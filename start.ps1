# Script PowerShell pour démarrer l'API de recettes

Write-Host "🍳 Démarrage de l'API de Recettes de Cuisine" -ForegroundColor Green
Write-Host "==========================================" -ForegroundColor Green

# Vérification de Java
Write-Host "📋 Vérification de Java..." -ForegroundColor Yellow
try {
    $javaVersion = java -version 2>&1 | Select-String "version" | ForEach-Object { $_.ToString() }
    Write-Host "✅ Java installé: $javaVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Java n'est pas installé ou accessible" -ForegroundColor Red
    exit 1
}

# Vérification de Maven
Write-Host "📋 Vérification de Maven..." -ForegroundColor Yellow
try {
    $mavenVersion = mvn -version 2>&1 | Select-String "Apache Maven" | ForEach-Object { $_.ToString() }
    Write-Host "✅ Maven installé: $mavenVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Maven n'est pas installé ou accessible" -ForegroundColor Red
    exit 1
}

# Vérification de MongoDB
Write-Host "📋 Vérification de MongoDB..." -ForegroundColor Yellow
$mongoService = Get-Service -Name "MongoDB" -ErrorAction SilentlyContinue
if ($mongoService) {
    if ($mongoService.Status -eq "Running") {
        Write-Host "✅ MongoDB est en cours d'exécution" -ForegroundColor Green
    } else {
        Write-Host "⚠️  MongoDB est installé mais arrêté. Tentative de démarrage..." -ForegroundColor Yellow
        try {
            Start-Service -Name "MongoDB"
            Write-Host "✅ MongoDB démarré avec succès" -ForegroundColor Green
        } catch {
            Write-Host "❌ Impossible de démarrer MongoDB" -ForegroundColor Red
            Write-Host "💡 Démarrez MongoDB manuellement ou utilisez Docker" -ForegroundColor Cyan
        }
    }
} else {
    Write-Host "⚠️  MongoDB n'est pas installé comme service Windows" -ForegroundColor Yellow
    Write-Host "💡 Vous pouvez utiliser Docker Compose: docker-compose up -d mongodb" -ForegroundColor Cyan
}

# Compilation du projet
Write-Host "`n🔧 Compilation du projet..." -ForegroundColor Yellow
try {
    mvn clean compile
    Write-Host "✅ Compilation réussie" -ForegroundColor Green
} catch {
    Write-Host "❌ Erreur lors de la compilation" -ForegroundColor Red
    exit 1
}

# Démarrage de l'application
Write-Host "`n🚀 Démarrage de l'application..." -ForegroundColor Yellow
Write-Host "📡 L'API sera accessible sur http://localhost:8080" -ForegroundColor Cyan
Write-Host "📚 Documentation disponible dans README.md" -ForegroundColor Cyan
Write-Host "`n⏹️  Appuyez sur Ctrl+C pour arrêter l'application" -ForegroundColor Yellow

try {
    mvn spring-boot:run
} catch {
    Write-Host "❌ Erreur lors du démarrage de l'application" -ForegroundColor Red
    exit 1
}