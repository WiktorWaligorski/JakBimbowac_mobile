# JakBimbować 🚍

JakBimbować is a simple Android public transport navigation app inspired by services like Jakdojade. The app allows users to browse transport lines and stops, search routes between locations (including transfers), and manage favorites.

---

## 📱 Features

- Browse all transport lines
- Browse all stops with connected lines and departure times
- Route search between stops (supports transfers between lines)
- Favorites system:
  - ⭐ Favorite lines
  - ⭐ Favorite stops
- Persistent storage using `SharedPreferences`
- Static transport data stored locally in JSON files
- Route finding based on BFS graph algorithm
- Simple Android UI using RecyclerView and Adapters
- Startup sound on app launch 🎵

---

## 🧠 Routing system

The routing engine is based on a graph model:

- Stops represent graph nodes
- Connections between consecutive stops are edges
- Each line creates connections in both directions
- BFS algorithm is used to find the shortest path in terms of number of stops
- Transfers between lines are detected automatically

### Example route

Miłostowo → Most Teatralny (Line 8)
Transfer at Most Teatralny
Most Teatralny → Górczyn (Line 14)

---

## 📂 Data format

All transport data is stored locally in JSON files inside the app.

### `lines.json`

Each line contains:
- line number
- color (RGB hex)
- forward and backward route
- stop-based schedule

### `stops.json`

Each stop contains:
- id
- name

---

## 🛠 Tech stack

- Java (Android)
- AndroidX (RecyclerView, ConstraintLayout, AppCompat)
- Gson (JSON parsing)
- SharedPreferences (favorites storage)
- BFS algorithm for route searching

---

## 🚀 Future improvements

- Dijkstra algorithm (fastest route based on time)
- Real-time departures
- GPS-based nearest stop detection
- Map integration (Google Maps)
- Improved UI/UX design
- Notifications for upcoming departures

---

## 📌 Notes

This project is for educational purposes and demonstrates:

- Graph-based route planning
- Android application structure
- Local JSON data handling
- Public transport network simulation
- State persistence using Android storage APIs