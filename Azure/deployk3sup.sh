#!/bin/bash
set -e

export NODE_1="40.88.151.72"
export NODE_2="52.188.109.241"
export NODE_3="52.152.130.169"
export USER="azureuser"

# The first server starts the cluster
k3sup install \
  --cluster \
  --ssh-key Master1_key.pem \
  --user $USER \
  --ip $NODE_2 \
  --k3s-extra-args '--write-kubeconfig-mode 664 --no-deploy=traefik '

k3sup install --ip 52.146.32.48 --user azureuser --cluster --ssh-key Master1_key.pem --k3s-extra-args '--no-deploy=traefik --bind-address=52.146.32.48 --advertise-address=52.146.32.48 --node-ip=52.146.32.48'

# The second node joins
k3sup join \
  --agent \
  --ssh-key azure.pem \
  --ip $NODE_1 \
  --user $USER \
  --server-user $USER \
  --server-ip $NODE_2 \
  --k3s-extra-args '--write-kubeconfig-mode 664 --no-deploy=traefik'

The third node joins
k3sup join \
  --agent \
  --ssh-key azure.pem \
  --ip $NODE_3 \
  --user $USER \
  --server-user $USER \
  --server-ip $NODE_2 \
  --k3s-extra-args '--write-kubeconfig-mode 664 --no-deploy=traefik'


K10cf72e28be731e12aba035aa000e5241b5046e75fd86bfc86a55ee023531d62f1::server:64b3165e8ebe662b93d6ac029507bff5
export K3S_URL="https://52.149.143.165:6443"
https://52.149.143.165:6443/api/v1/namespaces/default/services/ingweb/proxy/

az network dns record-set a add-record \
    --resource-group ingweb \
    --zone-name MY_CUSTOM_DOMAIN \
    --record-set-name * \
    --ipv4-address MY_EXTERNAL_IP