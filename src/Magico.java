package src;
import anexo.FileType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;



public class Magico {
	final static String slash=File.separator;
	public static void main(String[] args) {
		//Este programa necesita que le pasemos un argumento.
		String nombreDelFichero=args[0];

		String numeroMagico="";
		String extension="";
		extension=nombreDelFichero.substring(nombreDelFichero.lastIndexOf("."),nombreDelFichero.length());
		boolean conocido=false;
		
		//String numeroMagicoZIP	=	"504B0304";
		//String numeroMagicoPDF	=	"25504446";
		//String numeroMagicoPNG	=	"89504E47";
		//String numeroMagicoCLASS	=	"CAFEBABE";
		//String numeroMagicoGIF	=   "47494638";
		
		
		
		
		if(args.length!=1) {
			System.out.println("Este programa necesita que le pasemos un único argumento");
		}else {

			File archivo = new File("src"+slash+"datos"+slash+nombreDelFichero);
			if(!archivo.exists()) {
				System.out.println("El archivo no existe");
			}else {
				
				try {
				byte[] numeroMagicoArchivo = new byte[4];
				FileInputStream fisArchivo = new FileInputStream(archivo);
				fisArchivo.read(numeroMagicoArchivo, 0, 4);
				
				
				for(Byte b:numeroMagicoArchivo) {
					numeroMagico+=String.format("%02X", b);	//FORMATEA EL BYTE A String en HEXADECIMAL
				}
				//System.out.println("Numero magico de "+ archivo.getName()+" = "+numeroMagico);
				//System.out.println("Extension de "+ archivo.getName()+" = "+extension);
				
				
				
				
				


				//Almacenamos en el ArrayList types los objetos del fichero tipos.dat
				File tiposDAT = new File("src"+slash+"datos"+slash+"tipos.dat");
				ArrayList<FileType> types = new ArrayList<FileType>();

				FileType filetypeAUX= new FileType();

				
					FileInputStream fis= new FileInputStream(tiposDAT);
					ObjectInputStream ois = new ObjectInputStream(fis);

					//Rellena el ArrayList "types" con los objetos FileType del fichero.
					while(fis.available()>0) {	//HAY QUE LLAMAR AL FIS NO OIS

						filetypeAUX=(FileType) ois.readObject();
						types.add(filetypeAUX);
						//FileType añadido
					}

					
					
					
					String typeMagicNumber="";
					for(FileType tip:types) {
						typeMagicNumber="";
						for(int i:tip.getMagico()) {
							typeMagicNumber+=String.format("%02X", i);
						}//Concatena al string el numero mágico de ese tipo conocido
						
						
						if(numeroMagico.equals(typeMagicNumber)) {
							System.out.println("Contenido: "+tip.getDescripcion());
							System.out.println("Extension: "+(("."+tip.getExtension()).equals(extension)?"Correcta.":("Erronea. Se propone: "+tip.getExtension())));
							conocido=true;
						}
						
					}

					
					if(!conocido) {
						System.out.println("Contenido: desconocido.");
					}



					fisArchivo.close();
					ois.close();



				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

}
