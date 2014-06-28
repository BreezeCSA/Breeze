#include <stdio.h>

int main() {     /* 简单地循环打印标准输入上的两个整数之和 */
     int a, b, lineNumber = 0;
     while (scanf("%d %d", &a, &b))
         printf("Line# %d \t %d + %d == %d\n", ++lineNumber, a, b, a + b);


     return 0;
}

