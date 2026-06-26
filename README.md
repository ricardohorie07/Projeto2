# Projeto2

Aplicativo Android desenvolvido em **Kotlin** que exibe uma lista de episódios consumidos de uma API externa, usando uma `RecyclerView` com um `Adapter` customizado.

## 📱 Demonstração

<p align="center">
  <img src="docs/app.png" alt="Tela do aplicativo" width="300"/>
</p>

## ✨ Funcionalidades

- Tela principal (`MainActivity`) construída com layout XML.
- `RecyclerView` para exibir a lista de itens.
- `Adapter` customizado responsável por desenhar cada item da lista.
- Consumo de dados em tempo real da [Rick and Morty API](https://rickandmortyapi.com/) usando **Retrofit**.
- Conversão de JSON para objetos Kotlin com **Gson**.
- Chamadas de rede assíncronas com **Coroutines**, sem travar a interface.
- Cada item mostra: **nome**, **id**, **data de exibição** e **episódio**.

## 🧩 Estrutura do projeto

\`\`\`
app/src/main/
├── java/com/ricardo/projeto2/
│   ├── MainActivity.kt        # Configura a RecyclerView e busca os dados da API
│   ├── Serie.kt               # Modelo de dados (data class)
│   ├── SerieAdapter.kt        # Adapter + ViewHolder
│   ├── RickMortyApi.kt        # Interface com os endpoints da API
│   └── RetrofitClient.kt      # Configuração do Retrofit
└── res/
    ├── layout/
    │   ├── activity_main.xml  # Tela principal (título + RecyclerView)
    │   └── item_serie.xml     # Layout de um item da lista
    ├── drawable/
    │   └── bg_item_amarelo.xml
    └── values/
        └── colors.xml
\`\`\`

## 🛠️ Tecnologias

- Kotlin
- Android SDK
- RecyclerView (AndroidX)
- Retrofit + Gson
- Coroutines

## 🚀 Como executar

1. Clone o repositório.
2. Abra o projeto no Android Studio.
3. Aguarde o Gradle sincronizar.
4. Rode o app em um emulador ou dispositivo físico (requer conexão com a internet).

---

Desenvolvido por [Ricardo Horie](https://github.com/ricardohorie07).
