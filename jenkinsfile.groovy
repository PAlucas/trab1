import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.ListTagCommand
import org.eclipse.jgit.lib.Ref
import groovy.json.JsonSlurperClassic

class Config {
    def artefato
    def project
    def branch
    def url
}

def repoUrl = 'https://github.com/PAlucas/trab1.git'
def gettags = ("git ls-remote --tags ${repoUrl}").execute()


def select(gerar_artefato, artefato, branch, url, selected) {
    if (gerar_artefato) {
        item = new Config()
        item.artefato = artefato
        item.branch = branch
        item.url = url

        selected.add(item)
    }
}
def jsonParseAux(jsonAux) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}


@NonCPS
def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}

pipeline {
    agent any
    environment {
        selected = '[]'
        imageOk = '[]'
        build_ok = '[]'
        build_error = '[]'
    }
    parameters {
        booleanParam(name: 'deploy', defaultValue: true, description: 'Realizar o deploy no ambiente de qualidade')
        choice(name: 'front-end', choices: ['versoes'], description: '')
        booleanParam(name: 'gerar_front', defaultValue: false, description: '')
        choice(name: 'back-end', choices: ['versoes'], description: '')
        booleanParam(name: 'gerar_back', defaultValue: false, description: '')
    }
    options {
        timestamps()
    }
    stages {
        stage('Info') {
            steps {
                sh '''
                echo "PATH = ${PATH}"
                echo "DOCKER_HOST = ${DOCKER_HOST}"
                '''

                echo "VERSÃO: ${env.BUILD_TIMESTAMP_VERSAO}#${env.BUILD_NUMBER}"


                script {
                    try {
                        def artefato = "vendaweb-react"
                        def tagsOutput = sh(script: "git ls-remote --tags ${repoUrl}", returnStdout: true).trim()

                        def tags = []
                        // Parse tags from the output
                        println tagsOutput
                        tagsOutput.eachLine { line ->
                            def parts = line.split('\t')
                            def ref = parts[1].trim()
                            if (ref.startsWith("refs/tags/")) {
                                tags << ref.substring("refs/tags/".length())
                            }
                        }
                        
                        // Print the tags
                        tags.each { tag ->
                            println tag
                        }
                    } catch (err) {
                        echo err.getMessage()
                    }
                }
            }
        }
    }
}
