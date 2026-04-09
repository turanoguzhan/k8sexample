# k8s ConfigMap Demo

A Spring Boot application built to demonstrate **dynamic configuration management** on a Kubernetes cluster using ConfigMaps. Designed as a live presentation tool showing how Kubernetes handles configuration injection and zero-downtime rolling updates.

Docker image: [turanoguzhan/k8s-example](https://hub.docker.com/r/turanoguzhan/k8s-example)

---

## What This Demonstrates

- Injecting configuration into a Spring Boot app via Kubernetes ConfigMap
- Two ConfigMap strategies: **environment variables** and **volume-mounted file**
- **Zero-downtime rolling updates** when configuration changes
- Kubernetes **liveness and readiness probes** controlling traffic during rollouts
- Inspecting live configuration inside a running container with `kubectl exec`

---

## Project Structure

```
k8sexample/
├── k8s_app/                        # Spring Boot application
│   ├── src/
│   │   └── main/
│   │       ├── java/.../
│   │       │   ├── controller/
│   │       │   │   └── GreetingController.java   # REST endpoints
│   │       │   └── scheduler/
│   │       │       └── GreetingScheduler.java     # Prints greeting every 5s
│   │       └── resources/
│   │           └── application.properties         # Default config values
│   └── Dockerfile
└── k8s_files/                      # Kubernetes manifests
    ├── configmap-literal.yaml         # ConfigMap from key-value pairs (env var approach)
    ├── configmap-file.yaml            # ConfigMap from application.properties (volume approach)
    ├── deployment-init.yaml           # Deployment with no config (uses image defaults)
    ├── deployment-env.yaml            # Deployment with ConfigMap as env vars
    ├── deployment-config-volume.yaml  # Deployment with ConfigMap as mounted file
    └── service.yaml                   # NodePort service (port 30088)
```

---

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/` | Returns the `message` property value |
| GET | `/api/say-hi?name={name}` | Returns a greeting using the `greeting` property |
| GET | `/actuator/health/liveness` | Kubernetes liveness probe |
| GET | `/actuator/health/readiness` | Kubernetes readiness probe |

The scheduler prints the `greeting` value to stdout every 5 seconds — visible via `kubectl logs -f deployment/demo`.

---

## Configuration Properties

| Property | Default Value |
|----------|---------------|
| `greeting` | `Welcome` |
| `message` | `Catch me, if you can. (possibly you can but it worths to try.)` |

---

## Demo Scenarios

### Part 1 — No ConfigMap (image defaults)

Deploy with no external config. The app uses values baked into the image.

```bash
kubectl apply -f k8s_files/service.yaml
kubectl apply -f k8s_files/deployment-init.yaml
```

### Part 2 — ConfigMap as Environment Variables

Create the ConfigMap and deploy. Config is injected via `envFrom`.

```bash
kubectl apply -f k8s_files/configmap-literal.yaml
kubectl apply -f k8s_files/deployment-env.yaml
```

Inspect the injected config inside the container:
```bash
kubectl exec -it <pod-name> -- /bin/sh
env | grep -i greeting
```

Edit the ConfigMap and trigger a rolling update to see zero-downtime config change:
```bash
kubectl edit configmap demo-config
kubectl rollout restart deployment/demo
kubectl rollout status deployment/demo
```

### Part 3 — ConfigMap as Mounted File

Config is mounted as an `application.properties` file. Spring Boot reads it at startup via `SPRING_CONFIG_LOCATION`.

```bash
kubectl apply -f k8s_files/configmap-file.yaml
kubectl apply -f k8s_files/deployment-config-volume.yaml
```

Inspect the actual file inside the container:
```bash
kubectl exec -it <pod-name> -- /bin/sh
cat /config/application.properties
```
