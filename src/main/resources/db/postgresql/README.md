Run the schema manually in PostgreSQL when using the `postgres` profile:

```sql
\i src/main/resources/db/postgresql/schema.sql
```

Then start the app:

```bash
./gradlew bootRun --args='--spring.profiles.active=postgres'
```
