package com.hausung.hstuting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// 리사이클러뷰에 보여줄 각각의 채팅방 리스트 어댑터 구현
public class ChattingItemAdapterActivity extends RecyclerView.Adapter<ChattingItemAdapterActivity.ViewHolder> {
    Context context;

    //리스트뷰에서는 아이템을 위한 뷰를 보관하는데 이거는 데이터만 보관한다.
    ArrayList<ChattingItemActivity> items = new ArrayList<ChattingItemActivity>();


    //클릭이벤트처리 관련 사용자 정의(이 코드없으면 그냥 리사이클러뷰 구조)
    //참고로 OnItemClickListener는 기존에 있는것과 동일한 이름인이지만 같은 이름으로 정의
    //(리스트뷰에서는 이게 자동구현되있어서 OnItemClickListener를 구현안하고 호출해서 클릭시 이벤트를 처리할 수 있음)
    OnItemClickListener listener;

    public static interface OnItemClickListener {
        public void onItemClick(ViewHolder holder, View view, int position);
    }


    public ChattingItemAdapterActivity(Context context) {
        this.context = context;
    }

    @Override //어댑터에서 관리하는 아이템의 개수를 반환
    public int getItemCount() {
        return items.size();
    }


    @NonNull
    @Override //뷰홀더가 만들어지는 시점에 호출되는 메소드(각각의 아이템을 위한 뷰홀더 객체가 처음만들어지는시점)
    //만약에 각각의 아이템을 위한 뷰홀더가 재사용될 수 있는 상태라면 호출되지않음 (그래서 편리함, 이건내생각인데 리스트뷰같은경우는 convertView로 컨트롤해줘야하는데 이건 자동으로해줌)
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_chatting_item, viewGroup, false);//viewGroup는 각각의 아이템을 위해서 정의한 xml레이아웃의 최상위 레이아우싱다.

        return new ViewHolder(itemView); //각각의 아이템을 위한 뷰를 담고있는 뷰홀더객체를 반환한다.(각 아이템을 위한 XML 레이아웃을 이용해 뷰 객체를 만든 후 뷰홀더에 담아 반환
    }


    //각각의 아이템을 위한 뷰의 xml레이아웃과 서로 뭉쳐지는(결합되는) 경우 자동으로 호출( 즉 뷰홀더가 각각의 아이템을 위한 뷰를 담아주기위한 용도인데 뷰와 아이템이 합질때 호출)
    // Replace the contents of a view //적절한 데이터를 가져와 뷰 소유자의 레이아웃을 채우기 위해 사용(뷰홀더에 각 아이템의 데이터를 설정함)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {


        ChattingItemActivity item = items.get(position); //리사이클러뷰에서 몇번쨰게 지금 보여야되는시점이다 알려주기위해
        viewHolder.setItem(item); //그거를 홀더에넣어서 뷰홀더가 데이터를 알 수 있게되서 뷰홀더에 들어가있는 뷰에다가 데이터 설정할 수 있음
        //클릭리스너
        viewHolder.setOnItemClickListener(listener);

    }

    //아이템을 한개 추가해주고싶을때
    public void addItem(ChattingItemActivity item) {
        items.add(item);
    }

    //한꺼번에 추가해주고싶을때
    public void addItems(ArrayList<ChattingItemActivity> items) {
        this.items = items;
    }


    public ChattingItemActivity getItem(int position) {
        return items.get(position);
    }

    public void clear() {
        int size = items.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                items.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }
    //클릭리스너관련
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //뷰홀더
    //뷰홀더 객체는 뷰를 담아두는 역할을 하면서 동시에 뷰에 표시될 데이터를 설정하는 역할을 맡을 수 있습니다.
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject;
        TextView eachClass;
        TextView date;
        TextView time;
        TextView tutor;

        OnItemClickListener listenr; //클릭이벤트처리관련 변수

        public ViewHolder(@NonNull final View itemView) { //뷰홀더는 각각의 아이템을 위한 뷰를 담고있다.
            super(itemView);

            subject = itemView.findViewById(R.id.subject);
            eachClass = itemView.findViewById(R.id.eachClass);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            tutor = itemView.findViewById(R.id.tutor);

            //아이템 클릭이벤트처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listenr != null) {
                        listenr.onItemClick(ViewHolder.this, itemView, position);
                    }
                }
            });
        }

        //setItem 메소드는 객체를 전달받아 뷰홀더 안에 있는 뷰에 데이터를 설정하는 역할을 합니다.
        public void setItem(ChattingItemActivity item) {
            subject.setText(item.getsubject());
            eachClass.setText(item.getEachClass());
            date.setText(item.getDate());
            time.setText(item.getTime());
            tutor.setText(item.getTutor());
        }


        //클릭이벤트처리
        public void setOnItemClickListener(OnItemClickListener listenr) {
            this.listenr = listenr;
        }


    }
}
