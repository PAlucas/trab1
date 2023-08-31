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

def gitTags = ("git tag").execute()





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
        choice(name: 'versao', choices: ['rep = lucas.perlatto/mini-api.git', 'ola'], description: '')
        booleanParam(name: 'gerar_mini_api', defaultValue: false, description: '')
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

                


                echo "${tagsJson}"

                script {
                    try {
                        def artefato = "vendaweb-react"
                        
                        def tags = gitTags.text.readLines()
                                .collect { it.split()  }
                                .unique()
                                .findAll { it =~ /\d+\.\d+\.\d+/ }
                    } catch (err) {
                        echo err.getMessage()
                    }
                }
            }
        }
    }
}
