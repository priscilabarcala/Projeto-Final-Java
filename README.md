#  <i> :pushpin: Projeto final da disciplina de JAVA 1 :coffee:

## Folha de Pagamento :page_facing_up:


:computer: O projeto consiste na criação de um programa em Java que calcula o salário líquido de funcionários de uma empresa
de acordo com os conteúdos ministrados em aula.

## Conteúdo abordado no projeto

O programa envolve os seguintes conceitos e recursos:
- Interfaces
- Classes abstratas
- Classe concreta
- Encapsulamento
- Modificadores de Acesso
- Exceções
- Enum
- Arquivos
- Data do tipo LocalDate
- Coleções (Set, List, Map)
- Herança
- Separação por pacotes


### Tabela de Funcionários e Dependentes

Funcinários/Dependentes  |    CPF    |Nasc.   |   RG   |Sal/Parentesco
-------------------------|-----------|--------|--------|--------------|
Priscila Pinheiro Barcala|12278945698|19921120|25472001|   5000.00
Aurora Barcala           |14778954293|20190728|    -   |   SOBRINHO
Juliana Barcala          |32596314530|20200127|    -   |   SOBRINHO
Victor de Freitas        |18954796352|19940306|25793517|   4850.00
Ruan de Freitas          |87965785269|20180727|    -   |    FILHO
Ana de Freitas           |78942545612|20201104|    -   |    OUTRO
Paulo de Freitas         |04563985210|20080807|    -   |    FILHO
Erick da Silva Dahl      |25463589752|19960210|65478924|   4650.00
Camila da Silva          |18701465820|20101030|    -   |    OUTRO
Sergio Moreira Ribeiro   |74145898730|20170903|01325893|   3700.00
Adriano Moreira          |71265832180|20130422|    -   |   SOBRINHO
Nei de Souza Damazio     |96537842069|20130824|36486588|   3500.00
Carlos Damazio           |18265795364|20090713|    -   |    FILHO
Maria Damazio            |58736957890|20110819|    -   |    FILHO

### Cálculos :chart_with_upwards_trend:

#### Tabela do base de cálculo do INSS 

 Salário (R$)  |Alíquota %
|--------------|----------------------------------------|
até 1.751,81   | 8
entre 1.751,82 | até 2.919,72 9
entre 2.919,73 | até 5.839,45 11
acima 5.839,45 | 11% em cima do valor máximo de 5839,45

#### Tabela da base de cálculo do Imposto de Renda
Para o cálculo do Imposto, substraímos o salário bruto do valor pago de INSS 
e do valor por dependentes. Sendo realizada subtração, teremos o valor para cálculo 
do imposto conforme tabela da base de cálculo. Do resultado do cálculo faremos a 
subtração da dedução conforme a tabela para termos o valor a pagar do imposto.


 Base de cálculo       |Alíquota %  |Parcela a deduzir em R$
|----------------------|------------|-----------------------|
1903,98                |    -       |         -
de 1903,98 até 2826,65 |    7,5     |       142,80
de 2826,66 até 3751,05 |    15      |       354,80
de 3751,06 até 4664,68 |   22,5     |       636,13
acima de 4664,68       |   27,5     |       869,36



## Contribuidores

 :key:Erick da Silva
 :key:[Nei de Souza](https://github.com/Neidamazio)
 :key:[Priscila Pinheiro Barcala](https://github.com/priscilabarcala)
 :key:[Sérgio Moreira](https://github.com/SergioMRibeiro)
 :key:[Victor de Freitas](https://github.com/FrtsVictor)


## Mentoria
:key: Roni Schanuel
