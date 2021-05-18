#!/bin/bash
############################
#
# setup a service ci pipeline.
#
############################

PROFILE=dev
# the name of project.
PROJECT_NAME=lib-java-common
# the concourse info.
CI_TARGET=main
CI_TEAM=main

# delete pipeline.
fly -t ${CI_TARGET} dp \
  -p ${PROJECT_NAME}

# setup build pipeline.
fly -t ${CI_TARGET} set-pipeline \
  -p ${PROJECT_NAME} \
  -v git-privateKey="$(cat ~/.ssh/bpfaas/${PROJECT_NAME})" \
  -l ./vars-${PROFILE}.yaml \
  -l ~/.bpfaas/vars.yaml \
  -c ./template/pipeline.yml \
  --team=${CI_TEAM}

# unpause.
fly -t ${CI_TARGET} unpause-pipeline \
  -p ${PROJECT_NAME}