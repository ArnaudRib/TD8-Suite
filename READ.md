# TP8 Suite

### **Question 1 : Expression régulière.**


  - *Etape1 :*

--> ``t[^aeu][^ue][^reu][^aeu][^eu][^ue]``

  - *Etape 2 :*

--> ``tr[^ieu]v[^ieu]al``

  - *Etape 3 :*

--> ``travail``


### **Question 2 : Algorithme.**


- Proposer un mot au hasard,
- Lire la solution de ``evaluer()`` :
  - Si croix : rajouter la lettre au string 'Interdit' [^'Interdit']
- Lire une deuxième fois la solution de ``evaluer()`` :
  - Si rond : Conserver la lettre et la mettre dans le string du nouveau mot à rechercher.
  - Si tirret : Rajouter la lettre dans le bon [^].
- Chercher un mot correspondant aux nouvelles règles.
