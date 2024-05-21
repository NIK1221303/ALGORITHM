import java.util.ArrayList;

public class HeapSort implements Sort {
    public DataOnes data;

    public HeapSort(DataOnes data) {
        this.data = data;
    }

    public void sort() {
        data.retrieveData("Dataset1.txt");
        int n = data.getSet(2).getData().size();
        System.out.println(n); // odd means 2 child

      //  System.out.println("BEFORE: "+data.getSet(0).getData()); // initial data

        heapify(data.getSet(2).getData(), n);// make initial heap

       // System.out.println("HEAPIFY: "+data.getSet(0).getData()); // after initial heap

        pop(data.getSet(2).getData(),n);

        System.out.println("HEAP SORT: "+data.getSet(2).getData()); // finish sort


    }

    public void heapify(ArrayList<Integer> data, int heapsize) {

     


        int last_child = heapsize - 1; // firstly, find last child
       // System.out.println("@@@@@@"+last_child);

        int greater_child; // this store value of greater child (compare left and right, will compare this variable with parent value later)
                          
        int index;//the index for greater child

       

        for (int i = last_child; i > 1; i--) { //10,8,6,4,2


         //  System.out.println("-----");
            int parent = (i - 1) / 2; // find it parent index 
           // System.out.println("parent index: "+parent);

            if(!(i%2==0) && data.get(i)<data.get(parent)){ // !(i%2==0) indicate it have only 1 child (left), if left < parent skip current iteration
                continue;
            }

           // System.out.println("parent value: "+data.get(parent));

            if(i%2==0){ 

                

                if (data.get(i) < data.get(i - 1)) {// compare right child with left child
                    greater_child = data.get(i - 1); // assign greater child
                  //  System.out.println("greater child: "+greater_child+ " smaller child:"+data.get(i));

                    index = i - 1; // find greater child index
                } else {
                    greater_child = data.get(i);
                  //  System.out.println("greater child: "+greater_child+ " smaller child:"+data.get(i-1));

                    index = i;
                }

            }else{

               greater_child = data.get(i); 
              //   System.out.println("greater child: "+greater_child);
                 index = i;

    
            }

            if (greater_child > data.get(parent)) { // swap parent with greater children
                int temp = data.get(parent);
                data.set(parent, greater_child);
                data.set(index, temp);

                //after swap need to check if the parent got swapped still have greater child or not, call heapify() again 
                heapify(data, heapsize);

            }

            // if(i%2==0){
            //     System.out.println("["+data.get(parent)+","+data.get(i-1)+","+data.get(i)+"]"); // print new parent, left and right child


            // }else{
            //     System.out.println("["+data.get(parent)+","+data.get(i)+"]"); // print new parent, left and right child

            // }
            //parent = (i- 1) / 2; // find it parent
        }
        // System.out.print("HEAPIFY : ");
        // for(int num: data){
        //     System.out.print(num+",");
        // }

        // System.out.println("");

    }

    public void pop(ArrayList<Integer> data1, int n) { //largest at root , put into last index, minus 1 heapsize, call heapify

     //  int last_index = n-1;

       for(int i=n-1;i>0;--i){
        int temp=data1.get(0);
        //System.out.println("index 0: "+ data1.get(0));
      //  System.out.println("index last: "+ data1.get(i));

        data1.set(0, data1.get(i));
        data1.set(i, temp);
       // System.out.println("index 0: "+ data1.get(0));
        //System.out.println("index last: "+ data1.get(i));
        heapify(data1, i);
       }
      
    }
}