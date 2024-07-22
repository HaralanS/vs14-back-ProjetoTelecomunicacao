## Task Java Spring - Sistema de telecomunicações

Sistema de cadastro de clientes, escolha de plano, lançamento de faturas e pagamento de faturas para uma empresa de telecomunicações.

#### Sobre o sistema

O sistema consiste em uma api que fornece endpoints e lógica para funcionalidades de:

- Cadastro de cliente.
- Escolha de plano (Basico, Medium e Premium).
- Lançamento de todas as faturas relativas ao plano escolhido (feito automaticamente no cadastro e definicao do plano).
- Listagem de todas as faturas de determinado cliente.
- Pagamento de fatura.
- Delete lógico de cliente.

#### Sobre o processo de desenvolvimento

Para esse projeto iniciamos definindo a estrutura e fluxo do projeto e a partir disso criamos varios wireframes para termos uma ideia visual de quais funcionalidades seriam necessárias e como seriam implementadas, com isso criamos o diagrama de classe, após isso criamos um quadro kanban no trello e definimos as tasks, então subimos uma branch main e criamos uma branch develop a partir dela, e cada funcionalidade subia como uma branch feature que era incorporada na develop conforme aprovação e a develop incorporada na main conforme sucesso nos testes.
Em paralelo criamos um banco de dados postgres no render e conectamos no dbeaver, criamos as sequencias e tabelas necessarias e conectamos na nossa aplicação para testar as funcionalidades.

#### Passos para compilar e executar o programa

- Baixe a pasta ProjetoTelecomunicacoes deste repositorio.
- Abra o terminal e navegue ate a pasta src.
- Execute o comando "javac *.java".
- Execute o comando "java Main".

#### Diagrama de class UML

[Diagrama de classes](https://online.visual-paradigm.com/share.jsp?id=323931363730362d31)

#### Diagrama de relacionamento

![Logo do projeto](src/main/resources/images/telecomunicacoes_bd_postgres_er.png)

#### Quadro Kanban

- [Quadro Kanban no Trello](https://trello.com/invite/b/6699b75d58a4621d7c431ca7/ATTIa9c19814ed47d7a71a28a727800b5d55877145A7/documentacao-equipe-4-telecomunicacoes)

#### Projeto desenvolvido por

- [Juan Pablo](https://github.com/JuanpabloFAC)
- [Haralan Santana](https://github.com/HaralanS)
- [Igor Bueno](https://github.com/igorbuenov)
- [João Vitor Xavier](https://github.com/joaovxsantos)