# INBC
Integrated NGS Base Counter

## 1. Counter base frequency with consensus reads in sam/bam

```
samtools mpileup -f /data2/test/hla_data/ref_fasta/hg19.fasta /data2/test/lohhla_test/Test_Sub/S1930035/1824132_normal_sorted.bam -Q 0 -AB  > testA.pileup
grep -A 100 chr15$'\t'20495240 testA.pileup > test_section.pileup
```

 javac countBases.java

```
java countBases test_section.pileup
```

- similar with IGVTools and sequenza-util

```
./igvtools count -w 1 --bases --query chr15:20495240-20495340 /data2/test/lohhla_test/Test_Sub/S1930035/1824132_normal_sorted.bam test.wig /data2/test/hla_data/ref_fasta/hg19.fasta
```
#### output is test.wig
#### get IGVTools here  wget http://data.broadinstitute.org/igv/projects/downloads/2.4/igvtools_2.4.14.zip

or

```
samtools view -f 2 -u my.bam chr17:7570000–7590000 | samtools rmdup - - | samools mpileup -f hg19.fa -q 20 -  |  sequenza-utils.py pileup2acgt - | gzip > result.txt.gz
```

#### sequenza here   https://bitbucket.org/sequenzatools/sequenza-utils


## 2. Plot graph for A, T, C, G with gnuplot in targeted region
