# 🏦 Simulação Bancária

![Java](https://img.shields.io/badge/Java-21-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.6-brightgreen?logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)
![License](https://img.shields.io/github/license/jaasielsilva/SimulacaoBancaria)

## 📌 Descrição

Simulação Bancária é uma aplicação web desenvolvida em Java com Spring Boot, focada em operações bancárias básicas como **depósitos**, **saques** e **visualização do histórico de transações**.

Cada usuário possui sua própria conta e histórico, e os dados são armazenados de forma persistente em um banco de dados MySQL.

---

## 🚀 Tecnologias Utilizadas

- ☕ Java 21  
- 🌱 Spring Boot 3.4.6  
- 🐬 MySQL  
- 🍃 Spring Data JPA  
- 🔐 Autenticação simples (sem Spring Security)  
- 🧩 Lombok  
- 🧪 Maven  
- 🎨 Thymeleaf  
- 🎯 Bootstrap  

---

## ✅ Funcionalidades

- ✅ Cadastro e login de usuários (sem Spring Security)  
- ✅ Cada usuário acessa apenas sua conta  
- ✅ Depósitos e saques com validações  
- ✅ Histórico de transações detalhado  
- ✅ Saldo atualizado em tempo real  
- ✅ Interface web simples e intuitiva (Thymeleaf + Bootstrap)  
- ✅ Dados persistidos no banco de dados MySQL  

---

## 🛠️ Como Executar o Projeto

### 🔑 Pré-requisitos

- Java 21 instalado  
- Maven instalado  
- MySQL 8 ou superior em execução  

### ⚙️ Configuração do Banco de Dados

Crie o banco com o seguinte comando:

```sql
CREATE DATABASE simulacao_bancaria DEFAULT CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_unicode_ci';