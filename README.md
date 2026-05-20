# ⚽ Head Soccer — Proyecto Greenfoot

## Cómo abrir el proyecto

1. Abre **Greenfoot**.
2. Ve a `Scenario > Open` y selecciona la carpeta **HeadSoccer**.
3. Greenfoot cargará el archivo `project.greenfoot` automáticamente.
4. Haz clic en **Compile** (botón inferior derecho) para compilar todas las clases.
5. Presiona **Run** o el botón ▶ en pantalla para empezar.

---

## Controles

| Acción        | Jugador 1 (🔴) | Jugador 2 (🔵)  |
|---------------|---------------|-----------------|
| Mover izq.    | `A`           | `←`             |
| Mover der.    | `D`           | `→`             |
| Saltar        | `W`           | `↑`             |
| Golpear balón | `S`           | `↓`             |

---

## Reglas

- El **primero en anotar 5 goles** gana la partida.
- El balón entra a portería cuando cruza completamente el área de la red.
- Tras cada gol, los jugadores y el balón se reposicionan automáticamente.

---

## Archivos del proyecto

| Archivo              | Descripción                                      |
|----------------------|--------------------------------------------------|
| `SoccerWorld.java`   | Mundo principal, lógica de goles y marcador      |
| `Player.java`        | Jugador: movimiento, salto y patada              |
| `Ball.java`          | Balón: física, gravedad, rebotes y colisiones    |
| `Goal.java`          | Portería (izquierda y derecha)                   |
| `ScoreBoard.java`    | Marcador visual en pantalla                      |
| `StartButton.java`   | Botón clickeable para iniciar la partida         |
| `GoalLabel.java`     | Mensaje animado al anotar gol                    |
| `WinLabel.java`      | Mensaje de victoria al terminar                  |
| `project.greenfoot`  | Archivo de configuración del escenario           |
