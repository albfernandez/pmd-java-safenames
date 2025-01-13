# Release Process

This guide provides a chronological steps which goes through release tagging, staging, verification and publishing.

## Check the SNAPSHOT builds and pass the tests

```bash
mvn clean package install verify
```

## Set version and build 

```bash
# change release in pom.xml
mvn clean package install verify
mvn -Pdeploy clean package install verify deploy
git add -A
git commit -S -m 'Release <1.0.0>'
git tag -a <1.0.0> -m "Tagging release <1.0.0>"
git push
git push --tags
```


## Create release and upload artifacts to Github

Manually creating the release in Github project page, and upload generated artifacts.



## Prepare next iteration

```bash
# change release to SNAPSHOT in pom.xml
git add -A
git commit -S -m 'Next release cycle'
git push
```
