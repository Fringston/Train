## Projektrapport: WorkouteGenerate
### Fredrik Rinstad

### Beskrivning av projektet
Detta projekt är en applikation vars syfte är att generera träningspass.
Projektet uppdelat i två moduler där ena modulen, APIService, fungerar som ett webb API och den andra modulen, ClientApplication, fungerar som en klient som kommunicerar med webb API:et.
APIService-modulen använder Spring Boot som ramverk och ClientApplication är ett "vanligt" java-projekt.

I nuläget så finns endast funktionalitet för användaren att skapa ett konto, logga in, samt hämta alla användare från kafka ämnet *UserTopic*.

Informationen som användaren skriver in går via web-API:et och sedan vidare till en kafka-producer som skickar meddelandet till ett kafka-ämne.
Kafka-ämnet är i sin tur kopplat till en kafka-consumer som tar emot meddelandet och skriver in det i en mySQL databas. 
Det finns även ytterligare en consumer som skriver ut informationen i konsolen om användaren vill det. ("Get user information" i menyvalet).
Detta är egentligen tänkt att endast vara en admin-funktion då inte alla användare bör ha tillgång till den informationen.
Men i nuläget så har alla användare tillgång till det.

Egentligen så ska applikationen kunna hämta olika övningar från kafka-ämnen. Antingen slumpmässiga övningar eller övningar baserade på valda muskelgrupper.
Men detta är inte implementerat än. Jag har dock skapat 3 st kafka-ämnen för detta ändamål: *ExerciseTopic*, *ExerciseTopic* och *MuscleGroupTopic*.
Jag har även skapat 3 st tabeller i databasen för respektive ämne.

Även fast projektet inte är helt klart än så uppfyller det ändå kraven för inlämningen.

## Arbetet och dess genomförande 
### Vad som varit svårt 
Det har varit många utmaningar med detta projekt. Det var till exempel mycket krångel med att få de olika modulerna att fungera ihop.
Jag fick starta om projektet flera gånger då jag inte fick det att fungera.
En annan sak jag kämpade med länge var att få till consumern i klientapplikationen. 
Jag fick inte till att den skulle skriva ut informationen i konsolen.
  
### Beskriv något som var besvärligt att få till
Något som var lite besvärligt var att förstå och implementera alla steg som datan som användaren skriver in behövde ta för att ta sig på korrekt sätt via klient-app --> WebAPI --> KafkaProducer --> KafkaTopic --> KafkaConsumer --> mySQL-databas och klient-app.

Jag tyckte även det var besvärligt var att få till kopplingen mellan exercise- och muscleGroup-tabellerna i databasen. 
En kolumn i muscleGroup-tabellen är en foreign key till exercise-tabellen och jag har ännu inte lyckats få in data i den kolumnen genom att skicka via postman eller klientappen.

### Beskriv om du fått byta lösning och varför i sådana fall 
Jag var lite otålig i början och ville gå vidare med projektet så fort som möjligt. Jag glömde då bort kopplingen med kafka och kopplade istället direkt till databasen.
Detta gjorde att jag fick göra om en del senare då jag insåg att jag behövde kafka för att uppfylla kraven för inlämningen.

## Slutsatser 
### Vad gick bra 
Något som gick hyfsat bra var menyn i klientapplikationen. Jag börjar känna mig mer bekväm med att skapa menyer i java.
Och jag börjar få en tydligare bild över hur jag ska strukturera upp metoder och klasser. 

### Vad gick dåligt
Det som är dåligt är såklart att projektet inte är helt klart. Jag hade velat ha mer funktionalitet i klientapplikationen och att den skulle kunna hämta övningar från databasen.

### Vad har du lärt dig 
Jag har lärt mig mycket under detta projekt. Till exempel att använda olika branches på git och github. Detta har jag inte använt tidigare.
Jag har även lärt mig att använda multi-modul-projekt, kafka, mySQL-databas, JPA-repository, och allt vad det innebär.

### Vad hade du gjort annorlunda om ni gjort om projektet 
Jag har lite svårt att förstå syftet med kafka i detta projekt jag har valt, det känns som att det hade varit enklare att skicka och hämta datan direkt till databasen istället för att ha kafka som en mellanhand.
Jag kanske borde valt ett annat projekt där det hade varit mer lämpligt att använda kafka.

### Vilka möjligheter ser du med de kunskaper du fått under kursen.
Jag ser fram emot att få använda mig av de kunskaper jag fått under kursen i framtiden. 
Jag tror att det kommer vara till stor hjälp i mitt framtida arbete som utvecklare.