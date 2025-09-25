# 🌐 Sociala Nätverk - Gruppuppgift

## 📚 Beskrivning 
Detta är ett förenklat socialt nätverk implementerat i JAVA som en del av en utbildnign på Lexicon. 
Projektet är gjort som en gruppuppgift där vi delat arbetet mellan olika klasser och användartyper.

## ✨ Funktioner
- Skapa olika typer av användare: **RegularUser**, **AdminUser**, **Moderator**
- Posta meddelanden på det sociala nätverket
- Gilla och ogilla inlägg
- **Admin användare** kan ta bort inlägg
- **Moderator** kan hantera rapporterade inlägg
- Menybaserat gränssnitt via konsol

## 🏗️ Struktur
### 🧱 Klasser
- `User` – abstrakt klass för alla användare
- `RegularUser` – vanlig användare
- `AdminUser` – användare med administratörsrättigheter
- `Moderator` – användare som kan hantera rapporter
- `Post` – klass för inlägg, implementerar `Likeable`, `Named`, `Menu`, `Reportable`
- `SocialNetwork` – huvudklass som hanterar användare och inlägg
- `MenuHelper` – hjälpklass för konsolmenyer
- `SafeInput` – hjälpklass för säker användarinmatning

### 🧩 Interfaces
- `Likeable` – alla objekt som kan gillas eller ogillas
- `Menu` – alla användare kan visa sin meny
- `Named` – objekt som har ett namn