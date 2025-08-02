# Sistema de Gerenciamento de Cantores

Sistema completo para gerenciamento de cantores desenvolvido com **Spring Boot 3.5.3** e **Java 21**.

## 📋 Funcionalidades

- **🎵 Listagem de Cantores** (Pública)
- **➕ Cadastro de Cantores** (Admin)
- **✏️ Edição de Cantores** (Admin)
- **🗑️ Remoção de Cantores** (Admin)
- **🔍 Busca de Cantores por Nome**
- **🔐 Sistema de Autenticação e Autorização**
- **✅ Validação de Formulários**

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Data JPA / Hibernate**
- **Spring Security**
- **Thymeleaf**
- **MySQL 8.1** (Docker)
- **Bootstrap 5.3.2**
- **Maven**

## 📊 Estrutura do Banco de Dados

### Tabelas

- `tbl_role` - Papéis dos usuários (ADMIN, USER)
- `tbl_user` - Usuários do sistema
- `singer` - Cantores cadastrados

### Relacionamentos

- `User` ➡️ `Role` (Many-to-One)

## 🚀 Como Executar

### Pré-requisitos

- **Java 21** instalado
- **Maven 3.8+** instalado
- **Docker** e **Docker Compose** instalados

### Passo 1: Clonar o projeto

```bash
git clone <repository-url>
cd singer-management
```

### Passo 2: Subir o banco de dados MySQL

```bash
docker-compose up -d
```

Isso irá:
- Criar um container MySQL 8.1
- Criar o banco `singer_db`
- Executar o script `init.sql` com dados iniciais

### Passo 3: Executar a aplicação

```bash
mvn spring-boot:run
```

Ou compile e execute:

```bash
mvn clean package
java -jar target/singer-management-0.0.1-SNAPSHOT.jar
```

### Passo 4: Acessar a aplicação

Abra o navegador em: **http://localhost:8080**

## 👥 Usuários de Demonstração

| Usuário | Senha | Papel |
|---------|-------|-------|
| `admin` | `54321` | ADMIN |
| `diego` | `12345` | ADMIN |

## 🌐 Endpoints da Aplicação

### Públicos
- `GET /` - Página inicial (redireciona para `/singers`)
- `GET /singers` - Lista cantores (pública)
- `GET /login` - Página de login

### Autenticados (ADMIN)
- `GET /singers/add` - Formulário de cadastro
- `POST /singers/add` - Processar cadastro
- `GET /singers/edit/{id}` - Formulário de edição
- `POST /singers/edit/{id}` - Processar edição
- `GET /singers/delete/{id}` - Remover cantor

## 📝 Validações Implementadas

### Entidade Singer
- **Nome**: Obrigatório, 2-100 caracteres
- **Gênero**: Obrigatório, 2-50 caracteres  
- **Data de Nascimento**: Obrigatória, deve ser data passada
- **Nacionalidade**: Obrigatória, 2-50 caracteres

### Validações Frontend e Backend
- Validação de formulários com `@Valid` e `BindingResult`
- Mensagens de erro personalizadas em português
- Validação JavaScript adicional para datas

## 🔒 Segurança

### Configurações Spring Security
- **Páginas públicas**: `/`, `/singers`, `/login`, recursos estáticos
- **Páginas administrativas**: Requerem autenticação e papel `ADMIN`
- **Senhas**: Criptografadas com BCrypt
- **Sessões**: Gerenciadas automaticamente

### Controle de Acesso
- Listagem de cantores: **Pública**
- Operações CRUD: **Apenas ADMIN**

## 🎨 Interface do Usuário

### Design
- **Bootstrap 5.3.2** para interface moderna e responsiva
- **Bootstrap Icons** para ícones consistentes
- **Tema personalizado** com gradientes e animações
- **Layout responsivo** para dispositivos móveis

### Recursos UX
- Mensagens de feedback para ações do usuário
- Confirmação antes de remover cantores
- Breadcrumbs para navegação
- Auto-hide de alertas após 5 segundos

## 📁 Estrutura do Projeto

```
src/main/java/com/example/singermanagement/
├── config/
│   ├── SecurityConfig.java           # Configuração Spring Security
│   └── DataInitializer.java         # Inicialização de dados
├── controller/
│   ├── SingerController.java        # Controlador de cantores
│   └── MainController.java          # Controlador principal
├── model/
│   ├── Singer.java                  # Entidade Singer
│   ├── User.java                    # Entidade User
│   └── Role.java                    # Entidade Role
├── repository/
│   ├── SingerRepository.java        # Repositório Singer
│   ├── UserRepository.java          # Repositório User
│   └── RoleRepository.java          # Repositório Role
├── service/
│   ├── SingerService.java           # Serviço Singer
│   └── CustomUserDetailsService.java # Serviço autenticação
└── SingerManagementApplication.java  # Classe principal

src/main/resources/
├── templates/                       # Templates Thymeleaf
│   ├── singers.html                 # Lista de cantores
│   ├── add_singer.html             # Formulário cadastro
│   ├── edit_singer.html            # Formulário edição
│   └── login.html                  # Página de login
├── application.properties          # Configurações
└── init.sql                        # Script SQL inicial
```

## 🐳 Configuração Docker

### docker-compose.yaml
- **MySQL 8.1** na porta 3306
- **Volume persistente** para dados
- **Script de inicialização** automático
- **Configuração de rede** isolada

### Variáveis de Ambiente
- `MYSQL_ROOT_PASSWORD`: rootpassword
- `MYSQL_DATABASE`: singer_db
- `MYSQL_USER`: singer_user
- `MYSQL_PASSWORD`: singer_password

## 🔧 Configurações da Aplicação

### application.properties
```properties
# MySQL Database
spring.datasource.url=jdbc:mysql://localhost:3306/singer_db
spring.datasource.username=singer_user
spring.datasource.password=singer_password

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

# Thymeleaf
spring.thymeleaf.cache=false

# Server
server.port=8080
```

## 🧪 Testando a Aplicação

### Cenários de Teste

1. **Acesso Público**
   - Acessar `/singers` sem login
   - Visualizar lista de cantores
   - Buscar cantores por nome

2. **Login Administrativo**
   - Fazer login com `admin/54321`
   - Verificar menu administrativo
   - Testar logout

3. **Operações CRUD**
   - Adicionar novo cantor
   - Editar cantor existente
   - Remover cantor
   - Validar formulários

## 🚨 Troubleshooting

### Problemas Comuns

**MySQL não conecta:**
```bash
docker-compose down
docker-compose up -d
```

**Erro de dependências:**
```bash
mvn clean install
```

**Porta já em uso:**
- Alterar `server.port` em `application.properties`
- Ou parar processo na porta 8080

### Logs Úteis
```bash
# Ver logs do MySQL
docker-compose logs mysql

# Ver logs da aplicação
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dlogging.level.org.springframework.security=DEBUG"
```

## 📚 Próximos Passos

### Melhorias Possíveis
- [ ] Paginação na listagem de cantores
- [ ] Upload de fotos dos cantores
- [ ] Filtros avançados (gênero, nacionalidade)
- [ ] API REST para integração
- [ ] Testes automatizados
- [ ] Deploy com Docker

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais e demonstração das tecnologias Spring Boot.

---

**Desenvolvido com ❤️ usando Spring Boot 3.5.3 e Java 21**
