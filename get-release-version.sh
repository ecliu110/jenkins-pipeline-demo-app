#!/usr/bin/env bash
set -o errexit
set -o nounset

function git_tags() {
  git tag                            |
  grep -E '^v[0-9]+\.[0-9]+\.[0-9]+' |
  tr -d 'v'                          |
  sort -t. -k1,1n -k2,2n -k3,3n
}

function maven_version() {
  mvn org.apache.maven.plugins:maven-help-plugin:2.2:evaluate \
    -Dexpression=project.version                              \
    2>/dev/null                                               |
  grep -E '^[0-9]+\.[0-9]+\.[0-9]+(-SNAPSHOT)?'
}

BASE_VERSION=$(
  maven_version |
  cut -f1-2 -d"."
)

LAST_VERSION=$(
  git_tags                            |
  grep -E "^${BASE_VERSION}\.[0-9]+$" |
  tail -n1
)

if [[ -n "${LAST_VERSION}" ]]; then
  LAST_PATCH="$(echo "${LAST_VERSION}" | cut -f3 -d".")"
  NEXT_PATCH="$((LAST_PATCH + 1))"
  NEXT_VERSION="${BASE_VERSION}.${NEXT_PATCH}"
else
  NEXT_VERSION="${BASE_VERSION}.0"
fi

echo "${NEXT_VERSION}"
