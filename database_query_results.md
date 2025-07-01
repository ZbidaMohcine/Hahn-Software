# Database Query Results

## Connection Details
- **Database Name**: `db_personne`
- **Username**: `user_personne`
- **Host**: `localhost`
- **Port**: `5432`
- **Connection Status**: ✅ Successfully Connected

## Query Results from `personne` Table

The following data was retrieved from the `personne` table:

| ID | Age | Email             | Nom   | Prenom |
|----|-----|-------------------|-------|--------|
| 1  | 30  | adam@example.com  | Adam  | Idrissi|
| 2  | 25  | anass@example.com | Anass | Idrissi|

## Table Structure
The `personne` table has the following columns:
- `id` (bigint, primary key, not null)
- `age` (integer, not null)
- `email` (varchar(255))
- `nom` (varchar(255))
- `prenom` (varchar(255))

## Database Setup Information
- The database was initialized using Docker Compose with PostgreSQL 15
- Initial data was populated through the `init.sql` script
- The database contains 2 records in the `personne` table

## Query Executed
```sql
SELECT * FROM personne;
```

## Date and Time
Query executed successfully on: $(date)