ARG CI_REGISTRY

FROM $CI_REGISTRY/dep/library/project-oci-images/runtime-proxy-oauth2-v7:update1

USER root

COPY certs/ /usr/local/share/ca-certificates/

RUN update-ca-certificates

USER 1001
