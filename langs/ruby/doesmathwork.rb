def math_works
  1 + 1 == 2
end

puts "[ruby] Checking to see if math works..."

if math_works
  puts "[ruby] Math works."
  Process.exit(0)
else
  puts "[ruby] ERROR: Math doesn't work."
  Process.exit(1)
end
