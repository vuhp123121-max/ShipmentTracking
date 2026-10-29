# ShipmentTracking — Theo Dõi Quá Trình Làm Dự Án

- **Đường dẫn:** `/d/project/ShipmentTracking`
- **Ngôn ngữ/Frameworks:** Java 21, Spring Boot 4.1.1, Maven
- **Base hiện tại:** Java core + Spring Boot WebMVC + Data JPA
- **Document lộ trình:** `/d/project/ShipmentTracking/docs/`

## Lộ trình (theo docs/)

| Giai đoạn | Tiêu đề | Trạng thái | Ghi chú |
|---|---|---|---|
| PHASE0 | Model Shipment | ✅ DONE | 13/10/2026 |
| PHASE1 | Linux & Bash | ❌ Chưa | Cần học CLI, process, env |
| PHASE2 | Repository | ✅ DONE | 17/10/2026 (dùng HashMap) |
| PHASE3 | Testing & Validation | 🔄 Đang làm | Đã tạo 3 file test (ShipmentTest, ShipmentRepositoryTest, ShipmentServiceTest) |
| PHASE4 | Database & Persistence | ❌ Chưa | Đã ý tưởng: đổi HashMap → DB |
| PHASE5 | Docker & Docker Compose | ❌ Chưa | `docker-compose.yaml` có, `Dockerfile` trống |
| PHASE6 | Monitoring & Logging | ❌ Chưa | Cần Actuator, Prometheus, Grafana |
| PHASE7 | Cloud Deployment | ❌ Chưa | VPS/AWS |
| PHASE8 | CI/CD | ❌ Chưa | `ci.yml` (GitHub Actions) trống |
| PHASE9 | Kubernetes & IaC | ❌ Chưa | Terraform, Ansible |
| PHASE10 | Spring Boot REST API | ❌ Chưa | Chuyển từ menu terminal → API |
| PHASE11 | Cloud Deployment | ❌ Chưa | (liên quan PHASE7) |
| PHASE12 | Kubernetes & IaC | ❌ Chưa | (liên quan PHASE9) |

## Tình trạng tệp

| Tệp | Trạng thái |
|---|---|
| `pom.xml` | ✅ xong (Spring Boot 4.1.1, Java 21, MySQL connector) |
| `src/main/...Application.java` | ✅ xong (dấu chấm chính) |
| `src/main/...TestControler.java` | ✅ xong (endpoint `/ping`) |
| `src/main/...Shipment.java` | ✅ xong (model) |
| `src/main/...ShipmentRepository.java` | ✅ xong (in-memory HashMap) |
| `src/main/...ShipmentService.java` | ✅ xong (nghề nghiệp vụ, state machine) |
| `src/main/...ShipmentMenu.java` | ✅ xong (CommandLineRunner menu) |
| `docker/docker-compose.yaml` | ✅ xong (MySQL 8) |
| `docker/Dockerfile` | ❌ trống (0 bytes) |
| `.github/workflows/ci.yml` | ❌ trống (0 bytes) |
| `docs/PHASE8.md`, `docs/PHASE9.md` | ❌ trống (0 bytes) |

## Điểm quan trọng

- Tên database trong `docker-compose.yaml`: `shipment_tracking`
- MySQL root password: `123456` (nên đổi cái mạnh ở production)
- Cấu hình `src/main/resources` (application.properties/yaml) chưa thấy trong số tệp đã duyệt — cần kiểm tra `src/main/resources` vẫn còn hoặc imposes.

## Gợi ý bước tiếp theo (theo lộ trình)

1. **PHASE4 → Database:** đổi `HashMap` trong Repository ra `JpaRepository` / `@Entity` với MySQL.
2. **PHASE3:** bổ sung JUnit test cho `ShipmentService`, `ShipmentRepository`.
3. **PHASE5:** viết `Dockerfile` multi-stage cho Spring Boot + `docker-compose` (app + DB).
4. **PHASE6:** Spring Boot Actuator, `/actuator/health`, Prometheus, Grafana.
5. **PHASE8:** CI/CD GitHub Actions (build → test → image → push).
6. **PHASE10:** thêm REST Controller cho CRUD API (tạo, tra cứu theo tracking, lọc, cập nhật trạng thái).

## Trạng thái hiện tại

- App chạy được? → chưa chạy với DB (dùng vắn tạm HashMap)
- Từ điển vi tính? → chưa có
- CI pipeline? → chưa có
- Container? → chưa có Dockerfile
