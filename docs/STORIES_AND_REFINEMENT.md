# História do Produto & Refinamento Técnico
Repositório: lucas-santana-dev/facilita-plus  
Autor: Lucas Santana  
Data: 2025-10-26

Sumário
- Visão rápida
- Épicos (alto nível)
- Histórias de usuário (priorizadas) com critérios de aceitação
- Refinamento técnico por história (tarefas técnicas, modelos de dados, endpoints/collections)
- Non‑functional requirements (NFRs)
- Definição de pronto (DoD)
- Processo de grooming / template para novas histórias
- Próximos passos imediatos

---

## 1. Visão rápida
Facilita Plus é uma vitrine local que conecta clientes a profissionais autônomos em Jequié/BA e região. O MVP foca em descoberta, contato e reputação — sem pagamentos in‑app. Contato inicial via deep link (WhatsApp / telefone). Monetização: freemium (assinaturas para prestadores) + pay‑per‑lead no futuro.

---

## 2. Épicos (prioridade inicial)
1. E1 — Setup & Infra (Firebase, DI, CI) — já em andamento
2. E2 — Autenticação & Onboarding
3. E3 — Descoberta (Home, Busca, Categorias)
4. E4 — Perfil de Prestador (view + edição + portfólio)
5. E5 — Solicitações / Leads (ServiceLead flow / notificações / deep links)
6. E6 — Avaliações & Reputação
7. E7 — Monetização (Assinaturas + Painel)
8. E8 — Observabilidade, testes e deploy

---

## 3. Histórias de usuário (priorizadas, MVP)

Formato: [Epic] Título — descrição curta

E2-01 [Autenticação] Cadastro por email
- Como: usuário (cliente ou prestador)
- Quero: criar conta usando email/senha e escolher tipo (CLIENT / PROVIDER)
- Para: acessar funcionalidades personalizadas
- Critérios de aceitação:
  - Tela de cadastro com validação de email e senha forte
  - Ao completar cadastro, criar documento /users/{userId} com campo userType
  - Redirecionar para onboarding se primeiro login
  - Exibir mensagens de erro amigáveis (email já cadastrado, senha fraca)
- Estimativa: 3 SP (~4-8h)

E2-02 [Auth] Login social (Google)
- Critérios:
  - Login via Google implementado (Firebase Auth)
  - Consolida com usuário existente (mesmo email)
- Estimativa: 2 SP

E3-01 [Descoberta] Home com categorias e listagem por destaque
- Como: cliente
- Quero: ver categorias e prestadores em destaque
- Critérios:
  - Home exibe categorias (fixed set) e lista paginada de prestadores (paging 3)
  - Clicar em prestador abre ServiceDetails
- Estimativa: 5 SP

E3-02 [Busca] Busca por texto + filtros (cidade, rating, preço)
- Critérios:
  - Implementar busca simples Firestore (ou indexada)
  - Filtros funcionais, UI para ordenar por rating/distância
- Estimativa: 5–8 SP

E4-01 [Perfil] Visualizar perfil do prestador
- Critérios:
  - Exibe nome, descrição, categorias, portfolio images, rating, contato (WhatsApp/phone)
  - CTA "Solicitar Orçamento"
- Estimativa: 3 SP

E4-02 [Perfil] Editar perfil (prestador)
- Critérios:
  - Upload de imagens para Firebase Storage
  - Campos: businessName, description, categories, serviceArea (city, state, maxDistanceKm)
  - Validação e salvar em collection /serviceProviders/{userId}
- Estimativa: 5 SP

E5-01 [Leads] Criar solicitação (ServiceLead) — fluxo cliente
- Critérios:
  - Form com descrição, urgência, preferredContact (WHATSAPP / PHONE), fotos opcionais
  - Cria documento /serviceLeads/{leadId} com status = ACTIVE e expiresAt = now + 48h
  - Notificação push (FCM) para prestadores relevantes (por categoria/cidade)
  - Cliente recebe confirmação na UI
- Estimativa: 8 SP

E5-02 [Leads] Prestador recebe notificação e aceita lead
- Critérios:
  - Prestador vê lista de leads que pode aceitar (máximo X por lead)
  - Ao aceitar, lead.status -> MATCHED e providerId preenchido (ou múltiplos interestedProviders)
  - Sistema registra timestamp e notifica cliente
  - Ao aceitar, disponibilizar deep link pra WhatsApp com mensagem pré-formatada
- Estimativa: 5–8 SP

E6-01 [Avaliação] Cliente avalia prestador após serviço
- Critérios:
  - Permitir criar review (1-5, comentário, fotos)
  - Calcular rating médio do prestador e totalReviews
  - Mostrar nas telas do prestador
- Estimativa: 4 SP

---

## 4. Refinamento técnico por história (exemplo detalhado)
Vou detalhar E5 (Leads) por ser crítico para rastrear intenção e monetização futura.

### E5 (ServiceLead) — Tarefas técnicas
- Frontend:
  - Screen CreateLeadScreen (Compose)
  - Validate inputs, image picker (upload direto pra Storage)
  - Show confirmation + lead id
- Domain:
  - UseCase: CreateServiceLeadUseCase (valida dados, expiraAt calc)
- Data:
  - Repository: LeadRepository
  - Firestore:
    - Collection: /serviceLeads/{leadId}
    - Document fields:
      - id: string
      - clientId: string
      - providerId: string? (null até accept)
      - categoryId: string
      - description: string
      - urgency: string (URGENT, THIS_WEEK, FLEXIBLE)
      - preferredContact: string (WHATSAPP, PHONE)
      - photos: array<string> (storage URLs)
      - status: string (ACTIVE, MATCHED, EXPIRED, CANCELLED, CONVERTED)
      - createdAt: timestamp
      - expiresAt: timestamp
      - location: { city, state, lat?, lng? }
      - maxProviders: int (3)
      - interestedProviders: array<string>
- Notifications:
  - Cloud Function (optional) or Firestore-triggered Cloud Function:
    - On create serviceLeads: query providers in same category & city, send FCM messages
    - For MVP, trigger basic FCM via serverless function (ensure rate limits)
- Security rules (Firestore) — simplified:
```rules
service cloud.firestore {
  match /databases/{database}/documents {
    match /serviceLeads/{leadId} {
      allow create: if request.auth != null && request.resource.data.clientId == request.auth.uid;
      allow update: if request.auth != null && (
         // provider accepting
         (request.resource.data.providerId == request.auth.uid) ||
         // client can cancel
         (resource.data.clientId == request.auth.uid)
      );
      allow read: if request.auth != null;
    }
  }
}
```
- Indexes:
  - Composite: (categoryId ASC, location.city ASC, status ASC, createdAt DESC) for querying active leads by category/city.
- Analytics:
  - Event lead_created with params categoryId, city, urgency.

### Edge cases & retries
- Handle image upload failure: upload first, get URL, then create lead with photo URLs.
- Expiration worker: Cloud Function triggered by scheduler to set status -> EXPIRED e notify providers/clients.

---

## 5. Modelos de dados (resumo)
- /users/{userId}
  - id, email, name, phoneNumber, profilePictureUrl, userType (CLIENT|PROVIDER), createdAt
- /serviceProviders/{userId}
  - userId, businessName, description, categories[], serviceArea{city,state,maxDistanceKm}, rating, totalReviews, portfolioImages[], isVerified, priceRange
- /serviceLeads/{leadId}
  - (ver tabela acima)
- /reviews/{reviewId}
  - id, bookingId?, clientId, providerId, rating, comment, photos[], createdAt
- /subscriptions/{providerId}
  - providerId, plan, startDate, endDate, isActive, features

---

## 6. Non‑functional requirements (NFRs)
- Performance: Home screen list load < 2s on 4G
- Availability: Firebase SLA; app tolerates transient failures (show cached data via Room)
- Scalability: Design Firestore queries com índices; use pagination (Paging3)
- Security: Users authenticated for write operations; rules enforce ownership
- Privacy: No sensitive data in logs; nunca commit google-services.json
- Accessibility: Contrast & content descriptions for images; support TalkBack
- Offline: Read cache with Room; queue writes or inform user when offline

---

## 7. Definição de Pronto (DoD) para cada história
- Código mergeado em branch feature/* com PR aprovado
- Build CI green (assembleDebug)
- Unit tests para UseCases/ViewModels (>= basic)
- Lint/ktlint aplicado e warnings resolvidos
- Documentação mínima (README da feature ou comentários)
- Segurança: nenhuma credencial incluída
- Visual QA: screenshots anexados no PR para telas novas

---

## 8. Critérios de priorização e capacidade
- Você desenvolve no horário livre (noturno). Estimativa de capacidade:
  - 8–12 horas/semana => ~2–3 SP por semana (ajuste conforme disponibilidade)
- Priorizar:
  1) Infra & Auth (E1, E2)
  2) Discovery & Profiles (E3, E4)
  3) Leads (E5)
  4) Ratings (E6) e Observability (E8)
  5) Monetização (E7) depois de tração

---

## 9. Processos operacionais (grooming / refinement)
- Sprint length: 2 semanas (adequado para trabalho solo)
- Before starting a story:
  - Groom and break story into tasks (UI, VM, UseCase, Repo, Tests, Docs)
  - Estimate tasks in hours
  - Create GitHub issue and link PR to the issue
- PR checklist (use template):
  - Title: feat/<area>-short-description
  - Linked issue number
  - Description of changes and screenshots
  - Test instructions
  - CI status

---

## 10. Template de nova história (para abrir issues)
Title: [EPIC-CODE] Short title

Body:
- Descrição
- Como / Quero / Para
- Critérios de aceitação (bullet list)
- Tarefas técnicas (todo list)
- Estimativa (horas / SP)
- Dependências (issues)
- Designer (se aplicável)

---

## 11. ADRs (Iniciais)
1. ADR-001: Remover pagamentos in‑app — justifica evitar desintermediação; monetização via assinaturas/leads.
2. ADR-002: Chat in‑app adiado para V2 — utilizar deep links WhatsApp inicialmente.
3. ADR-003: Backend: Firebase (Firestore+Storage) — rápido para MVP, com Cloud Functions para notificações/cron jobs.

---

## 12. Checklist de entrega para Setup & Fundação (aplicar nas issues já criadas)
- [ ] Repo com README, .gitignore e LICENSE
- [ ] Firebase criado e configurações base documentadas (dev rules)
- [ ] Hilt + AppModule scaffold
- [ ] Theme + componentes base prontos
- [ ] Navigation Graph e telas placeholders
- [ ] CI (GitHub Actions) básico configurado

---

## 13. Próximos passos recomendados (curto prazo)
1. Implementar E2-01 e E2-02 (Auth) antes de expor telas públicas
2. Criar collections e regras mínimas no Firebase (dev)
3. Scaffold das telas Home/Profile com dados mock (para iteração de UX)
4. Priorizar E5 (ServiceLead) com notificações somente depois que Auth + Profile estiverem prontos

---

## 14. Quer que eu gere:
- Um arquivo Markdown pronto (este mesmo) no repo (posso criar via PR/draft)?
- Ou transformar cada história em issues detalhados (com checklists e estimativas) e criar no repo? 

Escolha a opção desejada e eu gero os artefatos (issues/arquivos/PRs) automaticamente ou preparo o conteúdo para você colar.