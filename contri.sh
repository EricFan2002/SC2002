EXTS=$(git ls-files | sed 's/^.*\.//g' | sort | uniq)
for EXT in $EXTS; do
  echo -e "\n$EXT\n"
  git ls-files | 
    grep ".$EXT" | 
    xargs -r -n1 git blame -w | 
    perl -n -e '/^.*?\((.*?)\s+[\d]{4}/; print $1,"\n"' | 
    sort -f | uniq -c | sort -n
done

