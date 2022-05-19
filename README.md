# Consenso Distribuído

Implementação do algoritmo de consenso distribuído descrito no artigo "Impossibility of Distributed Consensus with One Faulty Process", escrito por Michael J. Fischer, Nancy A. Lynch e Michael S. Paterson, publicado em 1985.

# 1. Autores

* Thiago Dias de Carvalho Quaresma Gama
* Ricardo Rodrigues Pereira

# 2. Requerimentos

1. O programa necessita para ser armazenado cerca de 68 KB de espaço de disco
2. A aplicação precisa ter acesso à internet para funcionar
3. As máquinas que executam as instâncias dos processos Run1, Run2 e Run3 devem estar na mesma rede LAN, seja ela cabeada ou sem fio

# 3. Pré-requisitos

1. Baixar e instalar Java Development Kit (JDK) (esse item é necessário para a instalação do Eclipse IDE)
2. Baixar e instalar Java Runtime Environment (JRE) (preferencialmente a última versão LTS lançada)
3. Baixar e instalar Eclipse IDE (preferencialmente a última versão lançada)

# 4. Passos para Execução

a) Usando Eclipse IDE:
 1. Abrir Eclipse IDE
 2. No menu de navegação superior, clicar na opção "File"
 3. Clicar na opção "Open Projects from File System..."
 4. Abrir o explorador de diretórios do computador com o botão "Directory..."
 5. Encontrar e selecionar o local onde está armazenada a pasta "Consenso-Distribuido"
 6. Marcar os campos corretos nas demais opções e caixas de seleção que precisam ser selecionadas para que seja realizada a importação
 7. Clicar no botão "Finish"
 8. Aguardar a importação do projeto no Package Explorer
 9. Na barra do console, procurar e clicar no ícone com um monitor com o símbolo da adição chamado "Open Console"
10. Clicar no menu para exibir suas opções e escolher a opção "1 New Console View"
11. Repetir a etapa 9 e 10 novamente
12. Caso se deseje ver os consoles lado a lado arrastar a aba do console para outra região do ambiente
13. No interior do diretório do projeto encontrar a pasta "src"
14. Dentro da pasta "src" abrir o pacote "aplicacao"
15. Dentro deste pacote executar a classe Run1.java
16. Por se tratar de uma aplicação que precisa de acesso à rede, seu firewall irá notificar que a aplicação OpenJDK Platform Binary necessita acessá-la. Nesse caso, permitir o acesso
17. No console, procurar e clicar em um botão com o ícone de um monitor com um pino chamado "Pin Console"
18. Selecionar outro console e executar a classe Run2.java
19. Repetir o passo 17
20. Selecionar o último console e executar a classe Run3.java
21. Repetir o passo 17

# Observações

1. Verificar se as bibliotecas empregadas estão atualizadas
2. Abrir dois consoles além do atual para a execução individual de cada processo
3. Liberar o acesso ao firewall para aplicações Java
4. Utilizar a opção "Pin Console" do Eclipse IDE para fixar o console do processo que se deseja acompanhar
5. Ler as informações mostradas no console da aplicação em execução atentamente para realizar um bom uso do programa

# Recursos de Desenvolvimento

1. Draw.io - Ferramenta de código aberto para modelagem de diagramas colaborativamente
2. Eclipse IDE 2019‑12 - Ambiente para desenvolvimento e teste de aplicações Java
3. GitHub - Plataforma de hospedagem de código-fonte com controle de versão usando o Git
4. Google Slides - Aplicação gratuita de criação e edição de apresentações compartilhadas
5. Notepad++ - Editor de texto e de código-fonte de código aberto
