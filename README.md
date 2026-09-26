# API de Gerenciamento de Imagens de Peças

Esta é uma aplicação web desenvolvida em Java com Spring Boot focada no gerenciamento, armazenamento e consulta de imagens relacionadas a peças. O projeto segue uma arquitetura limpa, separando responsabilidades entre controladores, serviços, domínios, infraestrutura e mapeadores (DTOs).

# 🚀 Tecnologias Utilizadas

Java (versão compatível com Maven Wrapper)

Spring Boot

Spring Data JPA (com suporte a especificações / Criteria API)

Docker & Docker Compose (para orquestração de serviços auxiliares)

Maven (Gerenciador de dependências)

📁 Estrutura do Projeto

O projeto está organizado em pacotes que respeitam princípios de arquitetura limpa:

com.example.imagensPecas/
│
├── aplication/
│   └── images/
│       ├── ImageDTO.java
│       ├── ImageMapper.java
│       ├── ImageServiceImpl.java
│       └── ImagesController.java
│
├── domain/
│   ├── entity/
│   │   └── Image.java
│   ├── enums/
│   │   └── ImageExtension.java
│   └── service/
│       └── ImageService.java
│
└── infra/
    └── repository/
        ├── ImageRepository.java
        └── specs/
            ├── GenericSpecs.java
            └── ImageSpecs.java


# ⚙️ Pré-requisitos

Certifique-se de ter instalado em sua máquina:

Java JDK (versão 17 ou superior recomendada)

Docker e Docker Compose (caso deseje rodar os serviços via container utilizando o docker-compose.yml)

# 🛠️ Como Executar o Projeto

1. Clonar o repositório e acessar a pasta da aplicação

cd AplicacaoWeb-main


2. Subir os serviços com Docker (se aplicável)

O projeto conta com um arquivo docker-compose.yml na raiz para facilitar a inicialização de dependências de infraestrutura:

docker-compose up -d


3. Executar a aplicação usando o Maven Wrapper

No Linux/macOS:

./mvnw clean spring-boot:run


No Windows (Prompt de Comando ou PowerShell):

mvnw.cmd clean spring-boot:run


# 🧪 Testes

Para executar os testes automatizados da aplicação, utilize o comando:

## Linux/macOS
./mvnw test

## Windows
mvnw.cmd test


# 📄 Contribuindo

Faça um Fork do projeto

Crie uma Branch para sua Feature (git checkout -b feature/NovaFeature)

Faça o Commit de suas alterações (git commit -m 'Adicionando nova feature')

Faça o Push para a Branch (git push origin feature/NovaFeature)

Abra um Pull Request
