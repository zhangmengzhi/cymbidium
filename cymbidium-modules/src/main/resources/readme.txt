提供系统运行监控 hyperic-sigar-1.6.4 （https://support.hyperic.com/display/SIGAR/Home）支持。
由于 hyperic-sigar 项目不在 maven 中央库，因此使用 jar 包直接依赖。

下载地址：http://ncu.dl.sourceforge.net/project/sigar/sigar/1.6/hyperic-sigar-1.6.4.zip

注意不同的操作系统需要将解压包后将相应的文件copy到java路径。比如windows32位操作系统需要将lib中sigar-x86-winnt.dll文件拷贝到java SDK目录的bin内。
* == Required to run on listed OS + Architecture combo
For example, minimal requirements to use the SIGAR Java API on Windows would be sigar.jar and sigar-x86-winnt.dll

Binaries
The SIGAR binary distribution contains the following files in sigar-bin/lib:

File	                   			Language	   	Description	                 	Required
sigar.jar	                 		Java       		Java API	                  	Yes (for Java only)
log4j.jar	                 		Java	    	Java logging API	          	No
libsigar-x86-linux.so	      		C	        	Linux AMD/Intel 32-bit	      	*
libsigar-amd64-linux.so	      		C	        	Linux AMD/Intel 64-bit	      	*
libsigar-ppc-linux.so	      		C	        	Linux PowerPC 32-bit	      	*
libsigar-ppc64-linux.so	      		C	        	Linux PowerPC 64-bit	      	*
libsigar-ia64-linux.so	      		C	        	Linux Itanium 64-bit	      	*
libsigar-s390x-linux.so				C				Linux zSeries 64-bit			*
sigar-x86-winnt.dll					C				Windows AMD/Intel 32-bit		*
sigar-amd64-winnt.dll				C				Windows AMD/Intel 64-bit		*
libsigar-ppc-aix-5.so				C				AIX PowerPC 32-bit				*
libsigar-ppc64-aix-5.so				C				AIX PowerPC 64-bit				*
libsigar-pa-hpux-11.sl				C				HP-UX PA-RISC 32-bit			*
libsigar-ia64-hpux-11.sl			C				HP-UX Itanium 64-bt				*
libsigar-sparc-solaris.so			C				Solaris Sparc 32-bit			*
libsigar-sparc64-solaris.so			C				Solaris Sparc 64-bit			*
libsigar-x86-solaris.so				C				Solaris AMD/Intel 32-bit		*
libsigar-amd64-solaris.so			C				Solaris AMD/Intel 64-bit		*
libsigar-universal-macosx.dylib		C				Mac OS X PowerPC/Intel 32-bit	*
libsigar-universal64-macosx.dylib	C				Mac OS X PowerPC/Intel 64-bit	*
libsigar-x86-freebsd-5.so			C				FreeBSD 5.x AMD/Intel 32-bit	*
libsigar-x86-freebsd-6.so			C				FreeBSD 6.x AMD/Intel 64-bit	*
libsigar-amd64-freebsd-6.so			C				FreeBSD 6.x AMD/Intel 64-bit	*
