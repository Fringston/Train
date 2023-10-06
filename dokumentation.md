## Projektrapport: WorkouteGenerate
### Fredrik Rinstad

### Beskrivning av projektet
Detta projekt är en applikation vars syfte är att generera träningspass.
Projektet uppdelat i två moduler där ena modulen, APIService, fungerar som ett webb API och den andra modulen, ClientApplication, fungerar som en klient som kommunicerar med webb API:et.

I nuläget så finns endast funktionalitet för användaren att skapa ett konto, logga in samt hämta alla användare från kafka ämnet UserTopic.

Informationen som användaren skriver in går via web-API:et och sedan vidare till en kafka-producer som skickar meddelandet till ett kafka-ämne.
Kafka-ämnet är i sin tur kopplat till en kafka-consumer som tar emot meddelandet och skriver in det i en mySQL databas. 
Det finns även ytterligare en consumer som skriver ut informationen i konsolen om användaren vill det. (Get user information).
Detta är egentligen tänkt att endast vara en admin-funktion så att inte alla vanliga användare får tillgång till den informationen.
Men i nuläget så har alla användare tillgång till det.

Även fast projektet inte är helt klart än så uppfyller det ändå kraven för inlämningen.

### Vad ni har gjort 
## Arbetet och dess genomförande 
### Vad som varit svårt 
### Beskriv lite olika lösningar du gjort 
### Beskriv något som var besvärligt att få till 
### Beskriv om du fått byta lösning och varför i sådana fall 

## Slutsatser 
### Vad gick bra 
### Vad gick dåligt 
### Vad har du lärt dig 
### Vad hade ni gjort annorlunda om ni gjort om projektet 
### Vilka möjligheter ser du med de kunskaper du fått under kursen.
