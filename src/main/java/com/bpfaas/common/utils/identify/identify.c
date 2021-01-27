/* A Demo of generator for FaaS resource ID
 * -------------------
 * Fiathux Su
 * 2020-01-15
 */

#include <time.h>
#include <stdio.h>
#include "com_bpfaas_common_utils_identify_IdentityGenerator.h"

// raw resource ID in binary
typedef unsigned char FResID[16];

static unsigned int atom_st;          // ST counter
static unsigned int atom_se = 0;      // SE counter
static unsigned long long inst_rand;  // RAND

// HEX table
static char __hex_dict[16] = "0123456789abcdef";

// BigEndian/LittleEndian check
static union {
  unsigned char ord[8]; unsigned long long s;} __lechk = {{1,0,0,0,0,0,0,2}};
#define __IS_LE_ORDER (__lechk.s == 0x0200000000000001)
// convert little-endian to big-endian
static unsigned int _le2be_32(unsigned int le){ // 32bit
  if (__IS_LE_ORDER) {
    return ((le & 0xff000000) >> 24) |
      ((le & 0xff0000) >> 8) |
      ((le & 0xff00) << 8) |
      ((le & 0xff) << 24);
  }
  return le;
}
static unsigned long long _le2be_64(unsigned long long le) { // 64bit
  if (__IS_LE_ORDER) {
    return (unsigned long long)_le2be_32(le >> 32) |
      ((unsigned long long)_le2be_32(le & 0xffffffff) << 32);
  }
  return le;
}

// global initialize
void faasid_init(){
  // initliaze ST using linux tickcount
  struct timespec t;
  clock_gettime(CLOCK_MONOTONIC, &t);
  atom_st = (t.tv_sec * 1000 + t.tv_nsec / 1000000) % 0xffffff;
  // initialize RAND using kernel random
  FILE *fp = fopen("/dev/random", "rb");
  fread(&inst_rand, 8, 1, fp);
  fclose(fp);
  inst_rand = _le2be_64(inst_rand & 0xffffffffffff);
}

// 24bit atomic-add (X86 CAS statement)
static inline unsigned int atomic_add(unsigned int *p, const unsigned int a){
  unsigned int ret;
  asm (
      "lock xadd %1, (%2);"
      : "=a"(ret)
      : "a"(a), "r"(p));
  return ret & 0xffffff;
}

// FaaS resource ID generator
void faasid_gen(FResID out){
  *((unsigned long long *)(out+8)) = 0; // clean
  time_t t = time(NULL);
  *((unsigned int *)(out)) = _le2be_32((unsigned int)t);
  *((unsigned long long *)(out+2)) |= inst_rand;
  // fill atomic numbers
  unsigned int st = atomic_add(&atom_st, 1);
  unsigned int se = atomic_add(&atom_se, 1);
  *((unsigned int *)(out+9)) |= _le2be_32((unsigned int)se);
  *((unsigned int *)(out+12)) |= _le2be_32((unsigned int)st);
}

// aaS resource ID formatter
void faasid_fmt(char out[32], FResID in) {
  for (int i = 0; i < 16; i++) {
    out[2*i] = __hex_dict[in[i]>>4];
    out[2*i + 1] = __hex_dict[in[i] & 0xf];
  }
}

// demo program
//int main() {
//  faasid_init();
//  for (int i = 0; i < 10; i++){
//    FResID id;
//    faasid_gen(id);
//    char strid[33];
//    strid[32] = 0;
//    faasid_fmt(strid, id);
//    printf("%s\n", strid);
//  }
//}

/*
 * Class:     com_bpfaas_common_utils_identify_IdentityGenerator
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_bpfaas_common_utils_identify_IdentityGenerator_init(JNIEnv *env, jclass thisClass) {
    faasid_init();
}

/*
 * Class:     com_bpfaas_common_utils_identify_IdentityGenerator
 * Method:    gen
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_bpfaas_common_utils_identify_IdentityGenerator_gen(JNIEnv *env, jclass thisClass) {
    FResID id;
    faasid_gen(id);
    char strid[33];
    strid[32] = 0;
    faasid_fmt(strid, id);
    return (*env)->NewStringUTF(env, strid);
}
