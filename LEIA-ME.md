# Henrique

**Responsável por:** Tela Explorar, Filtro/Preferências, Mapa, Salvos e Newsletter,
mais o banco de dados, o Mock Data e o Design System (cores/temas/textos)

A Vick saiu do time — as telas de Mapa e Salvos que eram dela passaram para você.

## Como usar

1. Clone o repositório: `https://github.com/ucasort/patamar-app` (você já foi
   convidado como colaborador — aceite em https://github.com/ucasort/patamar-app/invitations).
2. Para cada arquivo desta pasta, copie para o caminho indicado na tabela abaixo.
   O caminho começa **a partir da raiz do projeto**.
3. Se o arquivo já existir no destino, pode substituir.
4. Depois de colar tudo, dê `git add`, `git commit` e `git push` — o commit
   sai em nome da sua própria conta do GitHub.

## Onde colocar cada arquivo

### Pasta `codigo_app/`

| Arquivo | Colocar em |
|---|---|
| `PatamarApp.kt` | `app/src/main/java/com/patamar/app/` |

### Pasta `codigo_utils/`

| Arquivo | Colocar em |
|---|---|
| `CategoryIcons.kt` | `app/src/main/java/com/patamar/app/core/utils/` |

### Pasta `codigo_data/`

| Arquivo | Colocar em |
|---|---|
| `EventDao.kt` | `app/src/main/java/com/patamar/app/data/local/db/` |
| `Migrations.kt` | `app/src/main/java/com/patamar/app/data/local/db/` |
| `PatamarDatabase.kt` | `app/src/main/java/com/patamar/app/data/local/db/` |
| `SavedEventDao.kt` | `app/src/main/java/com/patamar/app/data/local/db/` |
| `UserDao.kt` | `app/src/main/java/com/patamar/app/data/local/db/` |
| `MockDataSource.kt` | `app/src/main/java/com/patamar/app/data/local/mock/` |
| `Event.kt` | `app/src/main/java/com/patamar/app/data/model/` |
| `EventCategory.kt` | `app/src/main/java/com/patamar/app/data/model/` |
| `FilterPreferences.kt` | `app/src/main/java/com/patamar/app/data/model/` |
| `SavedEvent.kt` | `app/src/main/java/com/patamar/app/data/model/` |
| `User.kt` | `app/src/main/java/com/patamar/app/data/model/` |
| `AuthRepository.kt` | `app/src/main/java/com/patamar/app/data/repository/` |
| `EventRepository.kt` | `app/src/main/java/com/patamar/app/data/repository/` |
| `FilterRepository.kt` | `app/src/main/java/com/patamar/app/data/repository/` |
| `UserRepository.kt` | `app/src/main/java/com/patamar/app/data/repository/` |

### Pasta `codigo_explore/`

| Arquivo | Colocar em |
|---|---|
| `ExploreFragment.kt` | `app/src/main/java/com/patamar/app/ui/explore/` |
| `ExploreViewModel.kt` | `app/src/main/java/com/patamar/app/ui/explore/` |
| `HomeFragment.kt` | `app/src/main/java/com/patamar/app/ui/explore/` |
| `EventGridAdapter.kt` | `app/src/main/java/com/patamar/app/ui/explore/adapter/` |
| `EventListAdapter.kt` | `app/src/main/java/com/patamar/app/ui/explore/adapter/` |
| `FeaturedEventAdapter.kt` | `app/src/main/java/com/patamar/app/ui/explore/adapter/` |
| `SavedEventAdapter.kt` | `app/src/main/java/com/patamar/app/ui/explore/adapter/` |

### Pasta `codigo_filter/`

| Arquivo | Colocar em |
|---|---|
| `FilterActivity.kt` | `app/src/main/java/com/patamar/app/ui/filter/` |
| `FilterViewModel.kt` | `app/src/main/java/com/patamar/app/ui/filter/` |

### Pasta `codigo_map/`

| Arquivo | Colocar em |
|---|---|
| `EventDetailBottomSheet.kt` | `app/src/main/java/com/patamar/app/ui/map/` |
| `EventDetailContent.kt` | `app/src/main/java/com/patamar/app/ui/map/` |
| `EventDetailDialog.kt` | `app/src/main/java/com/patamar/app/ui/map/` |
| `EventDetailViewModel.kt` | `app/src/main/java/com/patamar/app/ui/map/` |
| `EventMarkerHelper.kt` | `app/src/main/java/com/patamar/app/ui/map/` |
| `MapFilterBottomSheet.kt` | `app/src/main/java/com/patamar/app/ui/map/` |
| `MapFragment.kt` | `app/src/main/java/com/patamar/app/ui/map/` |
| `MapViewModel.kt` | `app/src/main/java/com/patamar/app/ui/map/` |
| `NewsletterBottomSheet.kt` | `app/src/main/java/com/patamar/app/ui/map/` |

### Pasta `codigo_saved/`

| Arquivo | Colocar em |
|---|---|
| `SavedFragment.kt` | `app/src/main/java/com/patamar/app/ui/saved/` |
| `SavedViewModel.kt` | `app/src/main/java/com/patamar/app/ui/saved/` |

### Pasta `design_system_cores/`

| Arquivo | Colocar em |
|---|---|
| `chip_bg.xml` | `app/src/main/res/color/` |
| `chip_text.xml` | `app/src/main/res/color/` |

### Pasta `design_system_values/`

| Arquivo | Colocar em |
|---|---|
| `colors.xml` | `app/src/main/res/values/` |
| `dimens.xml` | `app/src/main/res/values/` |
| `strings.xml` | `app/src/main/res/values/` |
| `themes.xml` | `app/src/main/res/values/` |

### Pasta `drawables/`

| Arquivo | Colocar em |
|---|---|
| `bg_card_event.xml` | `app/src/main/res/drawable/` |
| `bg_gradient_card_bottom.xml` | `app/src/main/res/drawable/` |
| `bg_gradient_map_top.xml` | `app/src/main/res/drawable/` |
| `bg_pill_dark.xml` | `app/src/main/res/drawable/` |
| `ic_bookmark.xml` | `app/src/main/res/drawable/` |
| `ic_bookmark_outline.xml` | `app/src/main/res/drawable/` |
| `ic_calendar.xml` | `app/src/main/res/drawable/` |
| `ic_card_scrim.xml` | `app/src/main/res/drawable/` |
| `ic_cat_art.xml` | `app/src/main/res/drawable/` |
| `ic_cat_fair.xml` | `app/src/main/res/drawable/` |
| `ic_cat_food.xml` | `app/src/main/res/drawable/` |
| `ic_cat_free.xml` | `app/src/main/res/drawable/` |
| `ic_cat_music.xml` | `app/src/main/res/drawable/` |
| `ic_cat_party.xml` | `app/src/main/res/drawable/` |
| `ic_cat_sport.xml` | `app/src/main/res/drawable/` |
| `ic_cat_theater.xml` | `app/src/main/res/drawable/` |
| `ic_check_circle.xml` | `app/src/main/res/drawable/` |
| `ic_chevron_down.xml` | `app/src/main/res/drawable/` |
| `ic_chevron_right_line.xml` | `app/src/main/res/drawable/` |
| `ic_clock.xml` | `app/src/main/res/drawable/` |
| `ic_map.xml` | `app/src/main/res/drawable/` |
| `ic_map_pin.xml` | `app/src/main/res/drawable/` |
| `ic_pin_small.xml` | `app/src/main/res/drawable/` |
| `ic_search.xml` | `app/src/main/res/drawable/` |
| `ic_settings.xml` | `app/src/main/res/drawable/` |
| `ic_sliders.xml` | `app/src/main/res/drawable/` |

### Pasta `layouts/`

| Arquivo | Colocar em |
|---|---|
| `activity_filter.xml` | `app/src/main/res/layout/` |
| `bottom_sheet_event_detail.xml` | `app/src/main/res/layout/` |
| `bottom_sheet_map_filters.xml` | `app/src/main/res/layout/` |
| `bottom_sheet_newsletter.xml` | `app/src/main/res/layout/` |
| `fragment_explore.xml` | `app/src/main/res/layout/` |
| `fragment_home.xml` | `app/src/main/res/layout/` |
| `fragment_map.xml` | `app/src/main/res/layout/` |
| `fragment_saved.xml` | `app/src/main/res/layout/` |
| `item_event_compact.xml` | `app/src/main/res/layout/` |
| `item_event_featured.xml` | `app/src/main/res/layout/` |
| `item_event_grid.xml` | `app/src/main/res/layout/` |
| `item_event_saved.xml` | `app/src/main/res/layout/` |
| `item_pref_category.xml` | `app/src/main/res/layout/` |
| `item_pref_option.xml` | `app/src/main/res/layout/` |

### Pasta `testes/`

| Arquivo | Colocar em |
|---|---|
| `MockDataSourceTest.kt` | `app/src/test/java/com/patamar/app/data/local/mock/` |

### Pasta `docs/`

| Arquivo | Colocar em |
|---|---|
| `MapFragment_OpcaoB_MapLibre.kt.reference` | `docs/` |

Total: 85 arquivos.

> O repositório sozinho (com a minha parte) não compila: falta a sua parte
> (explorar, mapa, salvos, banco de dados). Assim que colar seus arquivos e
> commitar, o projeto fica completo.
> Não estão no zip: `local.properties` (é de cada máquina) e as pastas
> geradas (`build/`, `.gradle/`).

Login de teste: `teste@patamar.app` / `Teste@123`.
