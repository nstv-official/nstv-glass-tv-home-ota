#!/usr/bin/env bash
# Test and simulate dynamic weather overlay states on Glass TV Home via ADB broadcast.
set -euo pipefail

TV_HOST="${TV_HOST:-${1:-}}"
CONDITION="${2:-Thunderstorm}"

if [ -z "$TV_HOST" ]; then
    echo "Penggunaan: TV_HOST=<IP_TV> $0 <IP_TV> [Condition]"
    echo "Contoh:"
    echo "  $0 192.168.1.50 Thunderstorm   # Uji Badai Petir & Kilat"
    echo "  $0 192.168.1.50 Drizzle        # Uji Gerimis"
    echo "  $0 192.168.1.50 Rain           # Uji Hujan Lebat"
    echo "  $0 192.168.1.50 Reset          # Kembalikan ke cuaca live semula"
    exit 1
fi

TOOLS="$(cd "$(dirname "$0")" && pwd)"
PY="$TOOLS/venv/bin/python"
[ -x "$PY" ] || PY=python3

echo "⚡ Mengirim data input cuaca: '$CONDITION' ke TV ($TV_HOST)..."
export TV_HOST="$TV_HOST"
"$PY" -I "$TOOLS/tv.py" "am broadcast -a com.gathin.tvhome.SET_WEATHER -p com.gathin.tvhome --es condition '$CONDITION'"
echo "✅ Selesai! Silakan lihat tampilan layar TV Anda."
