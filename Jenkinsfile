node('master') {

	def mvn = '/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/mvn_3.6.3/bin/mvn'
	def seleniumUrl = 'http://172.17.0.3:4444/wd/hub'

	stage('checkout') {
		checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'https', url: 'https://github.com/QA-automation-engineer/test-automation-itc-6.git']]])
	}

	stage('Cross-browser testing') {
		parallel "Chrome": {
			def buildDir = 'target-chrome'
			sh "${mvn} clean test -Dtest=lesson12.LoginTest -Dbrowser=Chrome -Dselenium.url=${seleniumUrl} -DbuildDirectory=${buildDir} -Dmaven.test.failure.ignore=true"
			junit "${buildDir}/surefire-reports/*.xml"
		}, "Firefox": {
			def buildDir = 'target-firefox'
			sh "${mvn} clean test -Dtest=lesson12.LoginTest -Dbrowser=Firefox -Dselenium.url=${seleniumUrl} -DbuildDirectory=${buildDir} -Dmaven.test.failure.ignore=true"
			junit "${buildDir}/surefire-reports/*.xml"
		}
	}
}