// https://gist.github.com/hayderimran7/50cb1244cc1e856873a4

import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()
def hudsonRealm = instance.getSecurityRealm()
def strategy = instance.getAuthorizationStrategy()

println hudsonRealm.getClass()
println strategy.getClass()

if (hudsonRealm.getClass() == hudson.security.SecurityRealm$None) {
    hudsonRealm = new HudsonPrivateSecurityRealm(false)
    instance.setSecurityRealm(hudsonRealm)
    println "Created new SecurityRealm"
} else {
    println "Found existing SecurityRealm"
}

if (strategy.getClass() == hudson.security.AuthorizationStrategy$Unsecured) {
    strategy = new GlobalMatrixAuthorizationStrategy()
    instance.setAuthorizationStrategy(strategy)
    println "Created new AuthorizationStrategy"
} else {
    println "Found existing AuthorizationStrategy"
}

hudsonRealm.createAccount("admin","admin")
strategy.add(Jenkins.ADMINISTER, "admin")
instance.save()
