# Local Variables
locals {
  ocid_tenancy          = "ocid1.tenancy.oc1..aaaaaaaa2dtxmynfg573nzup6opm7wqmwixws6pwp6xgrumvpg43xzzag5la"
  ocid_compartment      = "ocid1.compartment.oc1..aaaaaaaalvztb7qs6ilgm3yqh2jac35x47egoxdsywjtop47y23mbxpyrkoa"
  ocid_user             = "ocid1.user.oc1..aaaaaaaaxr2en2o3bdfnrljtdh2mmbj4vxgwp5f2gvgv3w2ygzdq2vg3dyfq"
  oci_vm_image          = "ocid1.image.oc1.phx.aaaaaaaakgt35lyfplkaitq2v23fj4ixoeu2vsfsazaemdwhailydl6wyiia"
  fingerprint           = "bd:09:47:5f:23:64:35:63:ec:a4:c1:9f:e8:ee:df:31"
  api_key_path          = "/home/sjanarth/.oci/oci_api_key.pem"
  api_key_password      = "Welc0me123#$"
  ssh_public_key        = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC9UaVHt5aHaoHqUj7qwXck4AxLEUcaeA7PCtpz1ajqUz3R2yzMERjyXZts6GXLMnLUqb7/qD+e7Sbe9cI98lLHg6CqSj+pnz8sx7JUg2UqnH+477ukH4iITZ9/XZkmSH9/79vlM2tAAaszXEqi19G1IpI5l7lln/DLMtwgrg48WweMTAJgS1jz1DGFTv/+DhwFpa0XZmz62nK5V9BMXdVjJTgGSiysVuCIzyxekDQPmWCoH+1w+zFPFWuvYnGWo+gQJbNtQyg0uQe3VxAX2igVOgMU29cXbfJryrnU1KpFQzIrJODZGpi8VI2PY554Yt72jc4CfGm3J4Rb7CvZMaTN proinvestor"
  ssh_private_key       = "/home/sjanarth/.oci/proinvestor"
  region                = "us-phoenix-1"
}

# Gets a list of Availability Domains
data "oci_identity_availability_domain" "ad" {
  compartment_id = local.ocid_tenancy
  ad_number      = 1
}

# Configure the Oracle Cloud Infrastructure provider with an API Key
provider "oci" {
  tenancy_ocid          = local.ocid_tenancy
  user_ocid             = local.ocid_user
  fingerprint           = local.fingerprint
  private_key_path      = local.api_key_path
  private_key_password  = local.api_key_password
  region                = local.region
}

# Create the ProInvestor VCN
resource "oci_core_vcn" "proinvestor_vcn" {
  cidr_block      = "10.1.0.0/16"
  compartment_id  = local.ocid_compartment
  display_name    = "ProInvestorVcn"
  dns_label       = "proinvestorvcn"
}

# Create an Internet Gateway
resource "oci_core_internet_gateway" "proinvestor_internet_gateway" {
  compartment_id  = local.ocid_compartment
  display_name    = "ProInvestorInternetGateway"
  vcn_id          = oci_core_vcn.proinvestor_vcn.id
}

# Default Routing Table
resource "oci_core_default_route_table" "default_route_table" {
  manage_default_resource_id = oci_core_vcn.proinvestor_vcn.default_route_table_id
  display_name               = "DefaultRouteTable"
  route_rules {
    destination       = "0.0.0.0/0"
    destination_type  = "CIDR_BLOCK"
    network_entity_id = oci_core_internet_gateway.proinvestor_internet_gateway.id
  }
}

# Create a Subnet
resource "oci_core_subnet" "proinvestor_subnet" {
  availability_domain = data.oci_identity_availability_domain.ad.name
  cidr_block          = "10.1.20.0/24"
  display_name        = "ProInvestorSubnet"
  dns_label           = "proinvestorsub"
  security_list_ids   = [oci_core_vcn.proinvestor_vcn.default_security_list_id]
  compartment_id      = local.ocid_compartment
  vcn_id              = oci_core_vcn.proinvestor_vcn.id
  route_table_id      = oci_core_vcn.proinvestor_vcn.default_route_table_id
  dhcp_options_id     = oci_core_vcn.proinvestor_vcn.default_dhcp_options_id
}

# Allow TCP ingress
resource "oci_core_security_list" "proinvestor_security_list" {
  compartment_id = local.ocid_compartment
  vcn_id         = oci_core_vcn.proinvestor_vcn.id
  display_name   = "DefaultSecurityList"

  // allow inbound tcp traffic on all ports
  ingress_security_rules {
    protocol  = "6"         // tcp
    source    = "0.0.0.0/0"
    stateless = false
  }
}

# Create the ProInvestor VM instance
resource "oci_core_instance" "proinvestor_instance" {
   availability_domain   = data.oci_identity_availability_domain.ad.name
  compartment_id        = local.ocid_compartment
  display_name          = "ProInvestor"
  shape                 = "VM.Standard2.1"
  create_vnic_details {
    subnet_id         = oci_core_subnet.proinvestor_subnet.id
    display_name      = "Primaryvnic"
    assign_public_ip  = true
    hostname_label    = "proinvestor001"
  }
  source_details {
    source_type = "image"
    source_id   = local.oci_vm_image
    #boot_volume_size_in_gbs = "20"
  }
  metadata = {
    ssh_authorized_keys = local.ssh_public_key
    user_data           = base64encode(file(local.ssh_private_key))
  }
}