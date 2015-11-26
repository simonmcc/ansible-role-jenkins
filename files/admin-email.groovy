import jenkins.model.*

def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()

jenkinsLocationConfiguration.setAdminAddress('Andy McGuigan <andrew.mcguigan@hpe.com>')

jenkinsLocationConfiguration.save()

