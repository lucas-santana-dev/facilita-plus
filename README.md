# Facilita Plus

Facilita Plus é um aplicativo Android que conecta clientes a profissionais autônomos em Jequié/BA e região. O objetivo é facilitar a busca por serviços locais, permitindo cadastro, descoberta, avaliação e contato direto entre usuários e prestadores.

Status: Inicial (Setup & Fundação em progresso)

## Visão rápida
- Plataforma: Android (Kotlin + Jetpack Compose)
- Backend: Firebase (Auth, Firestore, Storage, FCM)
- Arquitetura: Clean Architecture (Presentation / Domain / Data)
- Package name sugerido: com.lucassantana.facilitaplus

## Links importantes
- Documentação de histórias e refinamento técnico: docs/STORIES_AND_REFINEMENT.md
- Issues: https://github.com/lucas-santana-dev/facilita-plus/issues

## Quick Start (desenvolvimento local)
1. Clone o repositório:
   ```bash
   git clone https://github.com/lucas-santana-dev/facilita-plus.git
   cd facilita-plus
   ```
2. Abra o projeto no Android Studio (Electric Eel ou superior recomendado).
3. Adicione `google-services.json` em `app/` após criar o projeto no Firebase (NÃO comite este arquivo).
4. Build & Run:
   ```bash
   ./gradlew assembleDebug
   ```

## Estrutura sugerida
- `app/` - módulo Android
- `app/src/main/java/com/lucassantana/facilitaplus/`
  - `core/` (DI, theme, utils)
  - `presentation/` (features por pasta: auth, home, provider, booking, subscription)
  - `domain/` (models, usecases, repository interfaces)
  - `data/` (remote/local, repos impls, mappers)

## Roadmap inicial (prioridade)
1. Setup & Fundação (Firebase, Hilt, Design System, Nav, CI)
2. Autenticação (email + Google) e onboarding
3. Descoberta (Home, busca, categorias)
4. Perfil de prestador e edição
5. Solicitações / Leads e notificações (MVP)

## Contribuindo
- Abra issues para bugs e novas histórias.
- Use branches: `feature/<descrição>`, `fix/<descrição>` e abra PRs contra `main`.
- Siga o template de PR (descreva mudanças, testes e como validar).

## Observações
- Nunca commit `google-services.json` ou chaves privadas.
- Para testes locais use emulador com Play Services (quando usar Firebase Auth/FCM).

---

Changelog inicial:
- 2025-10-26: README inicial atualizado com visão e quick start.

Contact: lucas-santana-dev (owner)