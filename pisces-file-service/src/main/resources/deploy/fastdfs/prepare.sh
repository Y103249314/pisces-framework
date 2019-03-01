#!/bin/bash

host=${1:-'192.168.1.7'}
passwd=${2:-'123456'}
package=${3:-'fdfs-storage-deploy'}
packfile=${package}.tar.gz

# install sshpass
# ==============================================================
echo detect sshpass cmd on localhost ...
if [ ! -f "/bin/sshpass" ] && [ ! -f "/usr/bin/sshpass" ]; then
  echo no sshpass found. install sshpass now.
  echo 
  yum -y install sshpass
else
  echo sshpass installed.
fi

# detect package
# ==============================================================
echo
echo detect storage deploy package on remote host ${host} ...
sshpass -p${passwd} ssh -o StrictHostKeyChecking=no -o ConnectTimeout=5 root@${host} > /dev/null 2>&1 << eof
if [ -f "${packfile}" ]; then
  if [ -f "${package}/deploy.single.sh" ]; then
    exit 0
  else
    tar --no-same-owner -xzvf ${packfile}
    exit 0 
  fi
else
  exit 1
fi
eof
ret=$?
echo detect package done! return code: $ret
if [ $ret -eq 0 ]; then
  exit $ret
fi

# upload package
# ==============================================================
echo
echo upload storage deploy package to remote host ${host}:/root/ ...
sshpass -p${passwd} scp -o StrictHostKeyChecking=no -o ConnectTimeout=5 ${packfile} root@${host}:/root/
ret=$?
if [ $ret -eq 0 ]; then
  sshpass -p${passwd} ssh -o StrictHostKeyChecking=no -o ConnectTimeout=5 root@${host} << eof
  tar --no-same-owner -xzvf ${packfile}
  exit $?
eof
  ret=$?
fi
echo upload package done! return code: $ret 
echo 
exit $ret
