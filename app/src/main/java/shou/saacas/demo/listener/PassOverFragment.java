package shou.saacas.demo.listener;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.atrealize.saacas.ssc.Packet;
import jp.atrealize.saacas.ssc.SSCReceivedStatus;
import jp.atrealize.saacas.ssc.SaacasSSCListener;

public class PassOverFragment extends Fragment {
    public PassOverFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pass_over, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        SaacasSSCListener.getInstance().listen(new SaacasSSCListener.Callback() {
            @Override
            public Packet onPacketReceived(Packet packet, SSCReceivedStatus status, int sequence) {
                Logger.info("SSC packet received (request_code='%s', data='%s', response_code='%s', status='%s', sequence='%d').", packet.getRequestCode(), packet.getData(), packet.getResponseCode(), status, sequence);
                return packet;
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        SaacasSSCListener.getInstance().close();
    }
}
