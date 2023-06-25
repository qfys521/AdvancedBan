echo =====Remove *.hidden=====
find . -name '*.hidden' -type f -print -exec rm -rf {} \;
echo =====Remove *.bak=====
find . -name '*.bak' -type f -print -exec rm -rf {} \;
echo =====Remove ./build=====
rm -rf ./build
echo =====Remove ./.gradle=====
rm -rf ./.gradle