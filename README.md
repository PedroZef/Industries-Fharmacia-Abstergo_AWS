# Relatório de Implementação de Serviços AWS

![Logotipo da Abstergo Industries](./assets/logotipo_corporativo.png)

**Data:** 11/12/2025  
**Empresa:** Abstergo Industries  
**Responsável:** Pedro Zeferino da Silva

---

## 1. Introdução

Este relatório documenta a modernização da infraestrutura da aplicação "Plataforma Virtual", realizada por Pedro Zeferino da Silva. O projeto focou na migração para a nuvem AWS, elencando 3 serviços principais **(Cognito, Elastic Beanstalk e RDS)** com o objetivo primário de **redução de custos imediatos**, **segurança de dados** e **otimização da gestão operacional**.

---

## 2. Arquitetura e Serviços Selecionados

![Arquitetura da Solução](./assets/infográfico_corporat.png)

A arquitetura baseia-se em uma aplicação **Java Spring Boot**, integrada aos seguintes serviços gerenciados:

1. **Amazon Cognito:** Gerenciamento de autenticação e identidade.
2. **AWS Elastic Beanstalk:** Orquestração de deploy e escalabilidade automática.
3. **Amazon RDS:** Banco de dados relacional gerenciado.

---

## 3. Conformidade com Padrões e Normas

A escolha destes três serviços não foi baseada apenas em custo, mas também na aderência a rígidos padrões de segurança e normas da indústria, essenciais para o comércio de **medicamentos** (conforme visto no Modelo ER):

* **Segurança de Dados (LGPD/GDPR):**
  * O **Amazon RDS** permite criptografia em repouso (at-rest) e em trânsito (in-transit), garantindo a proteção dos dados sensíveis dos clientes e pedidos.
  * O **Amazon Cognito** gerencia dados de identidade seguindo padrões de conformidade globais (SOC 1/2/3, PCI DSS, ISO 27001), retirando a responsabilidade de armazenar senhas diretamente no banco de dados da aplicação.

* **Padrões de Alta Disponibilidade (SLA):**
  * O **AWS Elastic Beanstalk** garante a padronização do ambiente (Environment Consistency), assegurando que o código em produção rode exatamente como no ambiente de homologação, reduzindo falhas humanas no deploy.

---

## 4. Benefícios Financeiros e Operacionais

* **Redução de OpEx:** Substituição de custos fixos de servidores físicos por modelo "Pay-as-you-go" (pague pelo que usar).
* **Elasticidade:** O Auto Scaling ajusta a capacidade conforme a demanda do e-commerce, evitando gastos com servidores ociosos durante a madrugada ou excesso de carga em datas comemorativas (ex: Black Friday).

---

## 5. Comparativo de Cenários: On-Premise vs. AWS Cloud

![Comparativo de Manutenção](./assets/infográfico_corporat_Manuten.png)

Para justificar a migração, foi realizada uma análise comparativa entre o ambiente legado (On-Premise) e a nova arquitetura proposta na AWS.

### 6. Cenário Anterior (  On-Premise   /   Legado)

O ambiente antigo sofria com rigidez infraestrutural e custos fixos elevados, independentemente do volume de vendas.

* **Infraestrutura:** Servidores físicos ou VPS dedicados com capacidade fixa (provisionado para o pico de acesso).
* **Banco de Dados:** Instalação manual em servidor, exigindo rotinas complexas de backup, tuning e patching de segurança (DBA).
* **Autenticação:** Sistema desenvolvido internamente ("in-house"), gerando custos de manutenção de código e riscos de segurança.
* **Escalabilidade:** Inexistente ou manual. Se o tráfego subisse repentinamente, o site ficava lento ou caía.

### 6.1. Cenário Atual (AWS Cloud)

A nova arquitetura traz elasticidade e custos variáveis.

* **Infraestrutura (Elastic Beanstalk):** O ambiente cresce e diminui conforme a demanda. Se não há usuários, a infraestrutura reduz ao mínimo.
* **Banco de Dados (RDS):** Serviço gerenciado. Backups são automáticos e a alta disponibilidade é configurável com poucos cliques.

* **Autenticação (Cognito):** Terceirizada para a AWS. Custo zero para até 50.000 usuários ativos mensais (no Free Tier), eliminando manutenção de código de login.

---

### 6.2. 📉 Benefícios Integrados

**Categoria de Custo Antes (Infraestrutura Legada)**             **Após (AWS Gerenciado).**
| :----------------- | :---------------------------------- | :----------------------------- |
:--------------------------------------------------------------------------------------------- |**Justificativa da Economia**

| Custos de Servidor R$ 80.000/mês                         |        **R$ 40.000/mês.**

| Manutenção Técnica R$ 30.000/mês                         |        **R$ 15.000/mês.**

| Segurança de Dados Manual e limitada Automatizada e auditável | **Escalabilidade Fixa Elástica (Auto Scaling).**

## 6.3 Planilha de Estimativa de Custos Mensais

A tabela abaixo demonstra a redução de custos operacionais (TCO) projetada para um cenário de carga média.

| **Categoria de Custo** |    **Antes (On-Premise)**       | **Depois (AWS Cloud)**   | **Justificativa Economia**                                                                   |
| :----------------- | :---------------------------------- | :----------------------------- | :--------------------------------------------------------------------------------------------- |
| **Computação**     | R$ 1.500,00 (Servidor Dedicado/VPS) | **R$ 450,00 (EC2 via Beanstalk)**  | **No Beanstalk, pagamos apenas pelas horas de computação utilizadas (Auto Scaling).**              |

| **Banco de Dados** | R$ 800,00 (Licença + Hardware)      | **R$ 350,00 (Amazon RDS db.t3)**   | **Eliminação de custos de hardware dedicado e redução drástica em horas de administração (DBA).** |

| **Mão de Obra (Ops)** | R$ 2.000,00 (Manutenção/Patching)  | **R$ 500,00 (Monitoramento)**    | **O Elastic Beanstalk gerencia a saúde da aplicação automaticamente, reduzindo horas da equipe de TI.** |

| **Energia/Cooling** | R$ 300,00                          | **R$ 0,00**| **Incluso no serviço AWS**                                                      |

| **Segurança/Auth** | R$ 600,00 (Manutenção de código)    | **R$ 0,00 (Amazon Cognito*)**      | **O Cognito possui nível gratuito generoso que atende a demanda inicial.**                   |

| **TOTAL MENSAL**   | R$ 5.200,00**                       |    **R$ 1.300,00**                | **Redução de ~75%**                                                     |

**Nota: Valores estimados com base em calculadora AWS para região us-east-1 e custos médios de mercado para infraestrutura física/VPS. O custo do Cognito é virtualmente zero para startups até atingir 50k MAUs.**

## 7. Considerações Finais

A implementação desta arquitetura na Abstergo Industries promove uma infraestrutura robusta e escalável. A utilização combinada de **Cognito, Elastic Beanstalk e RDS** garante que a empresa foque no negócio (venda de medicamentos) e não na manutenção de hardware, resultando em uma redução imediata de custos operacionais e de manutenção.

### 📚 Referências Oficiais AWS

**Amazon Web Services:**

* [https://aws.amazon.com/pt/free/](https://aws.amazon.com/pt/free/)

* [https://aws.amazon.com/pt/cognito/](https://aws.amazon.com/pt/cognito/)

* [https://aws.amazon.com/pt/elasticbeanstalk/](https://aws.amazon.com/pt/elasticbeanstalk/)

* [https://aws.amazon.com/pt/rds/](https://aws.amazon.com/pt/rds/)

Assinatura do Responsável pelo Projeto: Desevolvedor Pedro Zeferino da Silva

---

**Data:** /12/2025.
**Empresa:** Abstergo Industries.
**Responsável:** Pedro Zeferino da Silva.
