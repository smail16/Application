{{/*
Expand the name of the chart to a valid kubernetes name (RFC 1123).
*/}}
{{- define "application.name" -}}
{{- $chartName := .Chart.Name | default .Values.nameOverride | lower | trimAll "-" | trunc 63 | trimAll "-" }}
{{- $chartName := regexReplaceAllLiteral "[^-a-z0-9]" $chartName "-" }}
{{- printf "%s" $chartName }}
{{- end }}

{{/*
Expand the release name to a valid kubernetes name (RFC 1123).
*/}}
{{- define "application.release" -}}
{{- $releaseName := .Release.Name | default .Values.nameOverride | lower | trimAll "-" | trunc 63 | trimAll "-" }}
{{- $releaseName := regexReplaceAllLiteral "[^-a-z0-9]" $releaseName "-" }}
{{- printf "%s" $releaseName }}
{{- end }}

{{/*
Create a default fully qualified app name as a valid kubernetes name (RFC 1123).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "application.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- $fullName := .Values.fullnameOverride | lower | trimAll "-" | trunc 63 | trimAll "-" }}
{{- $fullName := regexReplaceAllLiteral "[^-a-z0-9]" $fullName "-" }}
{{- printf "%s" $fullName }}
{{- else }}
{{- $chartName := include "application.name" . }}
{{- $releaseName := include "application.release" . }}
{{- if contains $chartName $releaseName }}
{{- printf "%s" $releaseName }}
{{- else }}
{{- printf "%s-%s" $releaseName $chartName | trunc 63 | trimAll "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "application.labels" -}}
helm.sh/chart: {{ include "application.name" . }}
{{ include "application.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- if .Values.jetId }}
jetId: "{{ .Values.jetId }}"
{{- end }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "application.selectorLabels" -}}
app.kubernetes.io/name: {{ include "application.name" . }}
app.kubernetes.io/instance: {{ include "application.release" . }}
app: {{ include "application.release" . }}
{{- end }}

{{/*
Secret for docker registry
*/}}
{{- define "imagePullSecret" -}}
{{- with .Values.imageCredentials }}
{{- printf "{\"auths\":{\"%s\":{\"username\":\"%s\",\"password\":\"%s\",\"auth\":\"%s\"}}}" .registry .username .password (printf "%s:%s" .username .password | b64enc) | b64enc }}
{{- end }}
{{- end }}

{{/*
Pod annotations
*/}}
{{- define "podAnnotations" -}}
last-updated-epoch: {{ now | unixEpoch | quote }}
{{- with .Values.podAnnotations }}
{{- toYaml . | nindent 0 }}
{{- end }}
{{- end }}
