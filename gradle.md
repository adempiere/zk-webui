# Adempiere WebUI - ZK + Spring Boot + Gretty

This project is a web application built on top of Adempiere ERP using:

- [ZK Framework 10](https://www.zkoss.org/)
- Spring Boot 3.5.x
- Gretty as embedded server (Tomcat/Jetty)
- Gradle build system
- `.env` for dynamic environment configuration
- Custom WAR packaging

---

## 📦 Requirements

- Java 17 or higher
- Gradle 8+
- Git (optional)
- Linux/macOS/Windows

---

## 🛠️ Project Structure

```
zk-webui/
├── build.gradle
├── settings.gradle
├── webapp-tomcat/
│   ├── .env                        # Environment configuration
│   ├── server-template.xml         # Gretty server.xml template
│   ├── server.xml                  # Generated server configuration
│   ├── AdempiereEnvTemplate.properties
│   └── AdempiereEnv.properties     # Generated runtime environment file
├── theme/
├── js/
├── css/
├── zul/
├── images/
├── WEB-INF/
│   ├── src/
│   ├── web.xml
│   └── lib/ (custom or local dependencies)
└── ...
```

---

## ⚙️ Configuration via `.env`

Edit the `webapp-tomcat/.env` file with your runtime/environment values:

```env
ADEMPIERE_DB_HOST=localhost
ADEMPIERE_DB_PORT=5434
ADEMPIERE_DB_NAME=394
ADEMPIERE_DB_USER=adempiere
ADEMPIERE_DB_PASSWORD=adempiere
```

These values will be used to dynamically generate:

- `server.xml` for Gretty (based on `server-template.xml`)
- `AdempiereEnv.properties` (runtime config)

---

## WAR Build Overview

The custom WAR is assembled using directories such as:

- `theme/`, `js/`, `css/`, `images/`, `zul/`, `WEB-INF/`, etc.

Gretty will run the WAR directly using `appRunWar`.

---

## Local Execution (with embedded Tomcat)

###  1. Set the environment type (e.g., `tomcat`, `jboss`)

In Linux/macOS:

```bash
export ADEMPIERE_APPS_TYPE=tomcat
```

Or run directly in one line:

```bash
ADEMPIERE_APPS_TYPE=tomcat ./gradlew prepareServerSetting appRunWar
```

On Windows CMD:

```cmd
set ADEMPIERE_APPS_TYPE=tomcat
gradlew prepareServerSetting appRunWar
```

On PowerShell:

```powershell
$env:ADEMPIERE_APPS_TYPE = "tomcat"
./gradlew prepareServerSetting appRunWar
```

### ▶️ 2. Run the application with Gradle

```bash
./gradlew prepareServerSetting appRunWar
```

This command will:

- Load values from `.env`
- Generate `server.xml` and `AdempiereEnv.properties`
- Set `ADEMPIERE_APPS_TYPE` as an environment variable
- Launch Gretty using the custom WAR with `appRunWar`

---

## Accessing the Application

Once running, visit:

```
http://localhost:8080/webui/
```

> Ensure that your `contextPath` in `gretty` matches `/webui`.

---

## Common Issues

| Problem | Cause | Solution |
|--------|--------|----------|
| `Child name [/webui] is not unique` | Multiple deployments with same `contextPath` | Do not launch `appRunWar` inside `exec`; use Gradle directly |
| `Task 'appRunWar' not found` | Spring Boot is using `bootWar` | Disable with `bootWar.enabled = false` in `build.gradle` |
| `.env file not found` | `.env` is missing | Create `webapp-tomcat/.env` file |

---

## Debugging & Development

Run with verbose output for debugging:

```bash
./gradlew prepareServerSetting appRunWar --info
```

---

## ✅ Supported Variables

- `ADEMPIERE_APPS_TYPE` – Defines the runtime type (`tomcat`, `jboss`, etc.)
- `.env` variables – Used to populate `server.xml`, `AdempiereEnv.properties`, and other runtime files.

---
