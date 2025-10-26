# 01 - Setup: Inicializar projeto Android + build config

Issue: https://github.com/lucas-santana-dev/facilita-plus/issues/1

Resumo
- Objetivo: Criar o projeto Android (Kotlin + Jetpack Compose) com configuração de build mínima e estrutura inicial pronta para desenvolvimento do MVP.
- Estimativa: 2–4 horas
- Labels sugeridas: setup, android, high-priority
- Assignee: @lucas-santana-dev

Critérios de aceitação
- Projeto Android criado e versionado no repo.
- build.gradle.kts configurado com Kotlin, Compose e plugins necessários.
- .gitignore, README.md inicial e LICENSE presentes.
- Aplicação builda localmente (./gradlew assembleDebug) sem erros.
- Branch inicial (main) com commit inicial feito.

Tarefas técnicas (checklist)
- [ ] Criar projeto Android no Android Studio com package sugerido: br.com.plussapps.facilitaplus
- [ ] Configurar Gradle (Kotlin JVM target, composeOptions) e usar Kotlin DSL (build.gradle.kts)
- [ ] Adicionar plugins e dependências iniciais:
  - kotlin-android, kotlin-kapt/ksp (se usar), com.google.gms.google-services (mas não commitar google‑services.json)
  - Jetpack Compose (ui, material3, tooling)
  - Hilt (dependências e ksp)
  - Navigation Compose (dependência placeholder)
- [ ] Definir minSdk = 26, targetSdk = 34, compileSdk = 34
- [ ] Adicionar arquivos iniciais:
  - .gitignore (Android + IntelliJ)
  - README.md (conteúdo básico — link para docs/STORIES_AND_REFINEMENT.md)
  - LICENSE (ex.: MIT)
- [ ] Scaffold de MainActivity com Compose e tema básico
- [ ] Commit inicial com mensagem: "chore: initial Android project setup"
- [ ] Push para branch main (ou criar branch feature/initial-setup se preferir PR)

Arquivos/estruturas a criar
- settings.gradle.kts
- build.gradle.kts (raiz)
- app/build.gradle.kts
- app/src/main/AndroidManifest.xml
- app/src/main/java/com/lucassantana/facilitaplus/MainActivity.kt (Compose)
- core/theme files: app/src/main/java/.../ui/theme/Color.kt, Theme.kt
- .gitignore
- README.md (breve)
- LICENSE

Exemplo de snippet (build.gradle.kts — app) (resumo)
```kotlin
android {
  compileSdk = 34
  defaultConfig {
    applicationId = "br.com.plussapps.facilitaplus"
    minSdk = 26
    targetSdk = 34
    versionCode = 1
    versionName = "0.1.0"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.3"
  }
}

dependencies {
  implementation("androidx.core:core-ktx:1.11.0")
  implementation("androidx.activity:activity-compose:1.8.0")
  implementation("androidx.compose.material3:material3:1.1.0")
  // Hilt
  implementation("com.google.dagger:hilt-android:2.47")
  ksp("com.google.dagger:hilt-compiler:2.47")
  // Navigation Compose
  implementation("androidx.navigation:navigation-compose:2.7.0")
}
```

Notas de segurança e operação
- NÃO comitar google-services.json nem chaves privadas. Documentar no README como adicionar localmente.
- Para builds em CI, use secrets do GitHub Actions para credenciais (se necessário).
- Use .env ou gradle.properties seguro para keys locais.

Definição de pronto (DoD) para esta issue
- Pull request aberto (branch feature/initial-setup → main) com descrição e checklist.
- CI (build) passa no workflow básico.
- README atualizado com instruções para iniciar localmente e onde colocar google‑services.json.
- Merge feito após revisão ou aprovação.

Comandos git (exemplo para criar branch e PR localmente)
```bash
git checkout -b feature/initial-setup
# adicionar arquivos / commitar
git add .
git commit -m "chore: initial Android project setup"
git push origin feature/initial-setup
# abrir PR via GitHub UI ou gh cli
gh pr create --title "chore: initial Android project setup" --body "Scaffold inicial do app, build config e README."
```

Observações finais
- Esta documentação descreve o “como” e “o que” técnico para fechar a issue #1.  
- Podemos reutilizar este template para outras issues de infra/bootstrapping (criar em docs/issues/).
