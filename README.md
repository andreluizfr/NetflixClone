# NetflixClone

## Descrição
...

### Backend
- Java
- Spring
- Spring Security
- Spring Data JPA
- Hibernate
- Java JWT
- Lombok
- Mercadopago SDK

### Frontend
- HTML
- CSS, Sass
- Typescript
- React
- React Router DOM
- Axios
- React Redux / React Redux Toolkit
- React Query
- Radix UI
- Framer motion
- ZOD
- React Hook Form
- React Helmet
- react-responsive, react-spinners, react-toastify, react-loading-skeleton
- mercadopago/sdk-react


## Imagens

### Página Inicial
<img src="/web-app/images/homepage.PNG" width="100%" height="auto"/>

### Página de uma das etapas de Registro
<img src="/web-app/images/signup.PNG" width="100%" height="auto"/>

### Página de login mobile
<img src="/web-app/images/login-mobile.PNG" width="50%" height="auto"/>

### Página de seleção de perfil
<img src="/web-app/images/who-is-watching.PNG" width="100%" height="auto"/>

### Página dos conteúdos
<img src="/web-app/images/contentspage.PNG" width="100%" height="auto"/>

## Como iniciar a aplicação

### server

#### rodar em desenvolvimento (porta 8080)
Importe pelo Eclipse ou Spring Tools, configure o Tomcat e dê run
Ou
Abra pelo VsCode, instale a extensão do Spring e espere ser identificado o projeto e dê run

### web-app

#### rodar em desenvolvimento (porta 5173)
```npm install```

```npm run dev```

#### rodar em produção (porta 5173)
```npm install```

```npm run build```

```serve -s build```


## Como iniciar a aplicação em uma instância EC2

### Configurando o docker
```sudo yum update -y```
```sudo yum install docker```
```sudo service docker start```
```sudo usermod -a -G docker ec2-user```
```sudo chmod 666 /var/run/docker.sock```

### Configurando docker-compose
```sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose```
```sudo chmod +x /usr/local/bin/docker-compose```
```docker-compose version```

### Configurando git
```sudo yum install git```
```git version```
```git clone https://github.com/andreluizfr/NetflixClone```

### Iniciando com o docker-compose
```docker-compose -f "NetflixClone/infra-services/docker-compose.prod.yaml" up -d --build --remove-orphans```

```docker-compose -f "NetflixClone/user-api/docker-compose.prod.yaml" up -d --build --remove-orphans```
```docker logs -f user-api-container```
```docker-compose -f "NetflixClone/user-api/docker-compose.prod.yaml" down```

```docker-compose -f "NetflixClone/api-gateway/docker-compose.prod.yaml" up -d --build --remove-orphans```
```docker logs -f api-gateway-container```
```docker-compose -f "NetflixClone/api-gateway/docker-compose.prod.yaml" down```
