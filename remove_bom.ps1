$paths = @('src\main\java', 'src\test\java')
foreach ($path in $paths) {
    Get-ChildItem -Path $path -Recurse -Filter '*.java' | ForEach-Object {
        $content = Get-Content -Path $_.FullName -Raw
        [System.IO.File]::WriteAllText($_.FullName, $content, (New-Object System.Text.UTF8Encoding($false)))
        Write-Host "Cleaned BOM: $($_.FullName)"
    }
}
