#include <stdio.h>

int checkToSeeIfMathWorks() {
  return 1 + 1 == 2;
}

int main(void) {
  printf("%s\n", "[c] Checking to see if math works...");
  if (checkToSeeIfMathWorks()) {
    printf("%s\n", "[c] Math works.");
    return 0;
  } else {
    printf("%s\n", "[c] ERROR: Math doesn't work.");
    return 1;
  }
}
