#!/bin/sh

###############################
# Copyright (c) 2021 Originforest Co.,Ltd. All Rights Reserved.
# Author: brian.li
# Date: 2021-05-29 11:27
# Desc: 
#   SH_CI_TARGET
#   SH_CI_PIPELINE_NAME
#   SH_CI_PROFILE
#   SH_CI_TEAM
###############################
# npm i @maxbuild/internal-ci-cli -g
# maxbuild-internal-ci xxx
###############################

SH_CI_PIPELINE_NAME=lib-java-common

###############################
# deploy pk
###############################
SH_DEPLOY_PK=$HOME/.ssh/bpfaas/$SH_CI_PIPELINE_NAME

###############################
# add new pipeline
###############################
sh_install_pipe(){
  sh_remove_pipe $*

  # install
  fly -t ${SH_CI_TARGET} set-pipeline \
    -p ${SH_CI_PIPELINE_NAME} \
    -v git-privateKey="$(cat $SH_DEPLOY_PK)" \
    -l ./vars-${SH_CI_PROFILE}.yaml \
    -l ~/.bpfaas/vars.yaml \
    -c ./template/pipeline.yml \
    --team=${SH_CI_TEAM}

  # active it
  fly -t $SH_CI_TARGET unpause-pipeline -p "$SH_CI_PIPELINE_NAME" --team="$SH_CI_TEAM"
}

