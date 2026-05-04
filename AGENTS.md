# AGENTS.md - FitApp

## Project
Android fitness app (Kotlin 2.0.0, Jetpack Compose + Material 3, minSdk 29, targetSdk 35).

## Build commands
```bash
./gradlew assembleDebug          # Build debug APK (run from FitApp2/)
./gradlew test                    # Unit tests
./gradlew connectedAndroidTest    # Instrumentation tests (needs emulator)
```
- Dependencies version catalog: `FitApp2/gradle/libs.versions.toml`
- Missing `google-services.json` is expected (gitignored), app fails at runtime without it

## Architecture
Clean Architecture + MVI (hand-rolled, not a library).
- DI: Hilt — `@HiltAndroidApp` on `FitApplication`, `@AndroidEntryPoint` on Activities, `@HiltViewModel` on ViewModels
- DB: Room 2.6.1 → `AppDatabase` with `user_table` and `steps_table`
- Auth: Firebase Auth
- Session: DataStore Preferences (`user_session`)
- Sensor: `TYPE_STEP_COUNTER` hardware sensor via `StepCounter`
- Background: WorkManager → `StepCountWorker`

## MVI pattern
Every feature needs: `State`, `Event`, `Effect`, `ViewModel`, `Screen`.
- Base: `BaseViewModel<Event, State, Effect>` at `mvi/projectStructure/BaseViewModel.kt`
- `createInitialState()` = abstract, return idle state
- `handleEvent(event)` = abstract, dispatch to private methods
- `setState { }` = reducer-style state updates
- `setEffect { }` = one-shot side effects (toast, navigation)
- Collect effects via `LaunchedEffect(Unit) { viewModel.effect.collect { ... } }` in Composable
- Reference: `LoginViewModel` is the cleanest example

## Package layout
```
com.example.fitapp/
  data/local/       → Room DB, DataStore, StepCounter sensor, DI modules
  data/remote/      → Firebase Auth, DI modules  
  domain/entities/  → Room @Entity
  domain/model/     → Domain models
  domain/usecases/  → Use cases
  domain/worker/    → WorkManager workers
  presentation/ui/
    component/      → Reusable composables
    mvi/            → Base MVI + per-feature effect/event/state + BaseViewModel
    screens/        → Full screen composables
    theme/          → Colors, Typography, Theme
    viewModel/      → Hilt ViewModels
```
⚠ `AuthScreen` is in `presentation/ui/theme/screen/LoginScreen.kt` — move to `screens/` eventually.

## Entry points
- `LoginActivity` (launcher, exported=true) → auth + ACTIVITY_RECOGNITION permission
- `MainActivity` → dashboard after login
- `FitApplication` → initializes Firebase

## Testing
- JUnit 4, Espresso, Compose UI test — all configured but tests are template-only
- Min 70% coverage target on domain layer

## Gotchas
- `StepCounter.steps()` uses `suspendCancellableCoroutine` — hangs until sensor fires; only call in Worker/coroutine
- `SensorRepositoryImpl.getSteps()` computes today as `last - first` data point → needs both start and end of day data
- Release builds have ProGuard enabled — keep rules in `proguard-rules.pro` for Room, Hilt, Firebase, domain models
- Network security config at `res/xml/network_security_config.xml` — update when adding new API domains
